package ee.ttu.catering.config.dialect;

import org.hibernate.Session;
import org.hibernate.internal.SessionImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import java.sql.Connection;
import java.sql.SQLException;
 
public class IsolationSupportHibernateJpaDialect extends HibernateJpaDialect {
 
    private static final long serialVersionUID = 1L;
    private Logger logger = LoggerFactory.getLogger(IsolationSupportHibernateJpaDialect.class);
 
    /**
     * This method is overridden to set custom isolation levels on the connection
     * 
     * @param entityManager
     * @param definition
     * @return
     * @throws PersistenceException
     * @throws SQLException
     * @throws TransactionException
     */
    @SuppressWarnings("deprecation")
    @Override
    public Object beginTransaction(final EntityManager entityManager, final TransactionDefinition definition)
        throws PersistenceException, SQLException, TransactionException {
        boolean infoEnabled = false;
        boolean debugEnabled = false;
        Session session = (Session) entityManager.getDelegate();
        if (definition.getTimeout() != TransactionDefinition.TIMEOUT_DEFAULT) {
            getSession(entityManager).getTransaction().setTimeout(definition.getTimeout());
        }
 
        Connection connection = ((SessionImpl)session).connection();
//        infoEnabled = logger.isInfoEnabled();
        infoEnabled = false;
        debugEnabled = logger.isDebugEnabled();
        if (infoEnabled) {
            logger.info("Connection Info: isolationlevel={} , instance={} ", connection.getTransactionIsolation(), connection);
            logger.info("Transaction Info: IsolationLevel={} , PropagationBehavior={} , Timeout={} , Name={}",
                new Object[] { definition.getIsolationLevel(), definition.getPropagationBehavior(), definition.getTimeout(),
                        definition.getName() });
        }
        if (debugEnabled) {
            logger.debug("The isolation level of the connection is {} and the isolation level set on the transaction is {}",
                connection.getTransactionIsolation(), definition.getIsolationLevel());
        }
        Integer previousIsolationLevel = DataSourceUtils.prepareConnectionForTransaction(connection, definition);
        if (infoEnabled) {
            logger.info("The previousIsolationLevel {}", previousIsolationLevel);
        }
 
        entityManager.getTransaction().begin();
        if (infoEnabled) {
            logger.debug("Transaction started");
        }
 
        Object transactionDataFromHibernateJpaTemplate = prepareTransaction(entityManager, definition.isReadOnly(),
            definition.getName());
 
        return new IsolationSupportSessionTransactionData(transactionDataFromHibernateJpaTemplate, previousIsolationLevel,
            connection);
    }
 
    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.orm.jpa.vendor.HibernateJpaDialect#cleanupTransaction(java.lang.Object)
     */
    @Override
    public void cleanupTransaction(Object transactionData) {
        super.cleanupTransaction(((IsolationSupportSessionTransactionData) transactionData)
            .getSessionTransactionDataFromHibernateTemplate());
        ((IsolationSupportSessionTransactionData) transactionData).resetIsolationLevel();
    }
 
    private static class IsolationSupportSessionTransactionData {
 
        private final Object sessionTransactionDataFromHibernateJpaTemplate;
        private final Integer previousIsolationLevel;
        private final Connection connection;
 
        public IsolationSupportSessionTransactionData(Object sessionTransactionDataFromHibernateJpaTemplate,
            Integer previousIsolationLevel, Connection connection) {
            this.sessionTransactionDataFromHibernateJpaTemplate = sessionTransactionDataFromHibernateJpaTemplate;
            this.previousIsolationLevel = previousIsolationLevel;
            this.connection = connection;
        }
 
        public void resetIsolationLevel() {
            if (this.previousIsolationLevel != null) {
                DataSourceUtils.resetConnectionAfterTransaction(connection, previousIsolationLevel);
            }
        }
 
        public Object getSessionTransactionDataFromHibernateTemplate() {
            return this.sessionTransactionDataFromHibernateJpaTemplate;
        }
 
    }
 
}