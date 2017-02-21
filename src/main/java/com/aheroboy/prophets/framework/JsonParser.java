package com.aheroboy.prophets.framework;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jackson.JsonLoader;
import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.main.JsonSchemaFactory;

import java.util.Iterator;

/**
 * Created by sgyang on 2/20/17.
 */
public class JsonParser {
    private static final JsonSchemaFactory factory = JsonSchemaFactory.byDefault();
    public JsonNode load() throws Exception{
        JsonNode node = JsonLoader.fromString(
            "{\"title\":\"Entity\",\"type\":\"object\",\"properties\":{\"firstName\":{\"type\":\"string\"},\"lastName\":{\"type\":\"string\"},\"age\":{\"description\":\"Age in years\",\"type\":\"integer\",\"minimum\":0}},\"required\":[\"firstName\",\"lastName\"]}");
       Iterator<JsonNode> jsonNodeIterator = node.iterator();
        while(jsonNodeIterator.hasNext()){
            JsonNode jn = jsonNodeIterator.next();
            if(!jn.isContainerNode()){
                System.out.println(jn.toString());
            }else{
                loadChild(jn);
            }
        }
        JsonSchema schema = factory.getJsonSchema(node);
        return null;
    }

    public void loadChild(JsonNode jsonNode){
        Iterator<JsonNode> jsonNodeIterator = jsonNode.iterator();
        while(jsonNodeIterator.hasNext()){
            JsonNode jn = jsonNodeIterator.next();
            if(!jn.isContainerNode()){
                System.out.println(jn.toString());
            }else{
                System.out.println("-----------");
                System.out.println("[TYPE]" + jn.getNodeType() + "[TYPE]");

                loadChild(jn);
            }
        }
    }

    public static void main(String args[]) throws Exception{
        new JsonParser().load();
    }
}
