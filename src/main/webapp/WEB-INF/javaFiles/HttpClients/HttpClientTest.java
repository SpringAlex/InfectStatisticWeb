package HttpClients;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HttpClientTest {
    public static void analysisData(String html){
        //解析html为dom树
        String num_1=null;
        Document document= Jsoup.parse(html);
        Elements elements=document.select(
                "div[class=ptab-0]");
        Elements es=document.getElementsByClass(
                "ptab-0"
        );
        if(elements.size()==0){
            System.out.println("size==0");
        }
        else{
            System.out.println("size!=0");
        }
        if(es.size()!=0){
            System.out.println("es size!=0");
            num_1=es.first().text();
        }
        for(Element e :elements){
            num_1= e.getElementsByClass(
                    "VirusSummarySix_1-1-207_3haLBF VirusSummarySix_1-1-207_2ZJJBJ").first().text();
        }
        if(num_1!=null){
            System.out.println(num_1);
        }
    }

    public static void main(String[] args) {
        //1.生成httpclient，相当于该打开一个浏览器
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        //2.创建get请求，相当于在浏览器地址栏输入 网址
        HttpGet request = new HttpGet("https://voice.baidu.com/act/newpneumonia/newpneumonia/?from=osari_pc_3");
        try {
            //3.执行get请求，相当于在输入地址栏后敲回车键
            response = httpClient.execute(request);

            //4.判断响应状态为200，进行处理
            if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                //5.获取响应内容
                HttpEntity httpEntity = response.getEntity();
                String html = EntityUtils.toString(httpEntity, "utf-8");
                //输出网页信息
                System.out.println(html);
                System.out.println("开始分析程序");
                analysisData(html);
                System.out.println("分析程序结束");
            } else {
                //如果返回状态不是200，比如404（页面不存在）等，根据情况做处理，这里略
                System.out.println("返回状态不是200");
                System.out.println(EntityUtils.toString(response.getEntity(), "utf-8"));
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //6.关闭
            HttpClientUtils.closeQuietly(response);
            HttpClientUtils.closeQuietly(httpClient);
        }
    }
}

