package io.searchbox.core;

import com.github.tlrx.elasticsearch.test.annotations.ElasticsearchNode;
import com.github.tlrx.elasticsearch.test.support.junit.runners.ElasticsearchRunner;
import io.searchbox.client.JestResult;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.Assert.*;

/**
 * @author Dogukan Sonmez
 */

@RunWith(ElasticsearchRunner.class)
@ElasticsearchNode
public class DeleteByQueryIntegrationTest extends AbstractIntegrationTest {

    @Test
    public void searchWithValidQuery() {
        String query = "{\n" +
                "    \"term\" : { \"user\" : \"kimchy\" }\n" +
                "}";

        try {
            client.execute(new Index.Builder("\"user\":\"kimchy\"").index("twitter").type("tweet").id("1").build());
            DeleteByQuery deleteByQuery = new DeleteByQuery(query);
            deleteByQuery.addIndex("twitter");
            deleteByQuery.addType("tweet");

            JestResult result = client.execute(deleteByQuery);
            assertNotNull(result);
            assertTrue(result.isSucceeded());
        } catch (Exception e) {
            fail("Failed during the delete index with valid parameters. Exception:%s" + e.getMessage());
        }
    }


    @Test
    public void searchWithValidBoolQuery() {
        String query = "{\n" +
                "    \"term\" : { \"user\" : \"kimchy\" }\n" +
                "}";

        try {
            DeleteByQuery deleteByQuery = new DeleteByQuery(query);
            deleteByQuery.addIndex("cvbank");
            deleteByQuery.addType("candidate");
            JestResult result = client.execute(deleteByQuery);
            assertNotNull(result);
            assertTrue(result.isSucceeded());
        } catch (Exception e) {
            fail("Failed during the delete index with valid parameters. Exception:" + e.getMessage());
        }
    }

}
