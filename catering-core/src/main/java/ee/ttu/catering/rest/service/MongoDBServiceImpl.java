package ee.ttu.catering.rest.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;

@Service
public class MongoDBServiceImpl {

	@Autowired
	private DB mongoDb;
	
	public void create(Object object) {
		
		HashMap<String, Object> map = parseObjectToMap(object);
		DBCollection coll = getCollection(object);
		
		BasicDBObject basicDBObject = new BasicDBObject();
		for (Map.Entry<String, Object> entry : map.entrySet()) {
		    String key = entry.getKey();
		    Object val = entry.getValue();
		    basicDBObject.append(key, val);
		}
		coll.insert(basicDBObject);
	}
	
	
	public void update(Object object) {
		
		HashMap<String, Object> map = parseObjectToMap(object);
		
		DBCollection coll = getCollection(object);
		
		BasicDBObject basicDBObject = new BasicDBObject();
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			String key = entry.getKey();
			Object val = entry.getValue();
			
			basicDBObject.append(key, val);
		}

		DBCursor cursor = coll.find(new BasicDBObject("id", map.get("id")),new BasicDBObject("id", 1));
		try {
			if (cursor.hasNext()) {
				coll.update(cursor.next(), basicDBObject);
			}
		} finally {
			cursor.close();
		}
	}
	
	public void delete(Object object) {
		
		HashMap<String, Object> map = parseObjectToMap(object);

		DBCollection coll = getCollection(object);
		
		DBCursor cursor = coll.find(new BasicDBObject("id", map.get("id")), new BasicDBObject("id", 1));
		try {
			if (cursor.hasNext()) {
				coll.remove( cursor.next() );
			}
		} finally {
			cursor.close();
		}
		
	}


	private DBCollection getCollection(Object object) {
		return getCollection(getTableName(object));
	}

	private String getTableName(Object object) {
		Class<?> c = object.getClass();
		Table table = c.getAnnotation(Table.class);
		String tableName = table.name();
		return tableName;
	}
	
	private DBCollection getCollection(String collectionName) {
		final int megaByte = 1048576;
		
		DBCollection coll = mongoDb.getCollection(collectionName);
		if(coll.find() == null) {
			coll = mongoDb.createCollection(collectionName, new BasicDBObject("size", megaByte));
		}
		return coll;
	}

	private HashMap<String, Object> parseObjectToMap(Object object) {
		JsonFactory factory = new JsonFactory(); 
		ObjectMapper mapper = new ObjectMapper(factory); 
		
		TypeReference<HashMap<String,Object>> typeRef  = new TypeReference<HashMap<String,Object>>() {};
		
		HashMap<String, Object> map = null;
		try {
			String jsonStr = mapper.writeValueAsString(object);
			map = mapper.readValue(jsonStr, typeRef);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return map;
	}
	
}
