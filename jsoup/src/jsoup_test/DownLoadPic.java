package jsoup_test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class DownLoadPic {

    private static final String saveImgPath="C:\\Users\\ZSY\\Desktop\\imgs";
    //ͼƬ����·��

    public void getDoc() throws IOException{
        //������Ϊ����
        Document doc = Jsoup.connect("http://www.163.com/")
        		.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36").get();
        //��ȡ��׺Ϊpng��jpg��ͼƬ��Ԫ�ؼ���
        Elements pngs = doc.select("img[src~=(?i)\\.(png|jpe?g)]");
        //����Ԫ��
        for(Element e : pngs){
            String src=e.attr("src");//��ȡimg�е�src·��
            //��ȡ��׺��
            String imageName = src.substring(src.lastIndexOf("/") + 1,src.length());
            //����url
            URL url = new URL(src);
            URLConnection uri=url.openConnection();
            //��ȡ������
            InputStream is=uri.getInputStream();
            //д��������
            OutputStream os = new FileOutputStream(new File(saveImgPath, imageName));
            byte[] buf = new byte[1024];
            int l=0;
            while((l=is.read(buf))!=-1){
                os.write(buf, 0, l);
            }


        }
    }

    public static void main(String[] args) throws IOException {
        new DownLoadPic().getDoc(); //���÷���
    }
}


