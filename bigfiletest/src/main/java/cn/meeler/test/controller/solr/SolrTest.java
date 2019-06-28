package cn.meeler.test.controller.solr;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SolrTest {
    private   HttpSolrClient client;
    @Before
    public void initSolrClient(){
        client = new HttpSolrClient.Builder("http://192.168.51.63:8983/solr/picsearch")
                .withConnectionTimeout(5000)
                .withSocketTimeout(5000).build();
    }

    @Test
    public void querySolrWithJava() throws IOException, SolrServerException {
        String keyword = "海洋";
        SolrQuery solrQuery = new SolrQuery();
        StringBuffer sb= new StringBuffer();
        sb.append("alitag:*"+keyword+"*").append(" OR title:*"+keyword+"*").append(" OR label:*"+keyword+"*").append(" OR id:*"+keyword+"*").append(" OR userId:*"+keyword+"*");
        System.out.println(sb.toString());
        solrQuery.setQuery(sb.toString());
//        solrQuery.set("q","alitag:*"+keyword+"*");
//        solrQuery.setFilterQueries("title:xixi");
        solrQuery.addFilterQuery("direction:1 OR direction:2");
        solrQuery.addFilterQuery("typeid:2 OR typeid:3");
        solrQuery.set("wt","json");
        solrQuery.setSort("meeler(alitag,"+keyword+")", SolrQuery.ORDER.asc);
        solrQuery.setStart(0);
        solrQuery.setRows(100);
        QueryResponse response = client.query(solrQuery);
        SolrDocumentList results = response.getResults();
        List<Picture> pictures = response.getBeans(Picture.class);
        for (Picture picture : pictures) {
            System.out.println(picture);
        }
    }
    @Test
    public void meelerTest(){
       List<String> s= new ArrayList<String>();
       s.add("abc");
       List<String> r = s ;
       s.add("xyz");
       System.out.println(r);
    }
}
