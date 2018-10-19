/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.edu.ccnu.imd.gsmis.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;

import cn.edu.ccnu.imd.gsmis.entity.Distribute;
import cn.edu.ccnu.imd.gsmis.entity.Expert;
import cn.edu.ccnu.imd.gsmis.dao.DistributeDao;

/**
 * 论文分配管理Service
 * @author zjy
 * @version 2018-08-14
 */
@Service
@Transactional(readOnly = true)
public class DistributeService extends CrudService<DistributeDao, Distribute> {

	@Autowired
	public ExpertService expertService;
	
	@Autowired
	public DistributeResultService distributeResultService;
	

	
	public Distribute get(String id) {
		return super.get(id);
	}
	
	public List<Distribute> findList(Distribute distribute) {
		return super.findList(distribute);
	}
	
	public Page<Distribute> findPage(Page<Distribute> page, Distribute distribute) {
		return super.findPage(page, distribute);
	}
	
	@Transactional(readOnly = false)
	public void save(Distribute distribute) {
		super.save(distribute);
	}
	
	@Transactional(readOnly = false)
	public void delete(Distribute distribute) {
		super.delete(distribute);
	}
	
	
	
	//去掉字符串的逗号和空格
	public static String getText(String str){
    	char[] arr = str.toCharArray();
    	String str1 = null;
    	for (int i = 0; i < arr.length; i++) {
			if(arr[i] != '，' && arr[i] != ',' && arr[i] != ' ') {
				str1 += arr[i];
			}
				
			}
		
    	return str1;
    }		
	//添加余弦相似度算法，返回相似度
	public static double getSimilarDegree(String str1, String str2){  
        //创建向量空间模型，使用map实现，主键为词项，值为长度为2的数组，存放着对应词项在字符串中的出现次数  
         Map<String, int[]> vectorSpace = new HashMap<String, int[]>();  
         int[] itemCountArray = null;//为了避免频繁产生局部变量，所以将itemCountArray声明在此  
       //转换
         str1 = getText(str1);
         str2 = getText(str2);
       //以逗号为分隔符，分解字符串  
         String strArray[] = str1.split(",");  
         for(int i=0; i<strArray.length; ++i)  
         {  
             if(vectorSpace.containsKey(strArray[i]))  
                 ++(vectorSpace.get(strArray[i])[0]);  
             else  
             {  
                 itemCountArray = new int[2];  
                 itemCountArray[0] = 1;  
                 itemCountArray[1] = 0;  
                 vectorSpace.put(strArray[i], itemCountArray);  
             }  
         }  
           
         strArray = str2.split(" ");  
         for(int i=0; i<strArray.length; ++i)  
         {  
             if(vectorSpace.containsKey(strArray[i]))  
                 ++(vectorSpace.get(strArray[i])[1]);  
             else  
             {  
                 itemCountArray = new int[2];  
                 itemCountArray[0] = 0;  
                 itemCountArray[1] = 1;  
                 vectorSpace.put(strArray[i], itemCountArray);  
             }  
         }  
           
         //计算相似度  
         double vector1Modulo = 0.00;//向量1的模  
         double vector2Modulo = 0.00;//向量2的模  
         double vectorProduct = 0.00; //向量积  
         @SuppressWarnings("rawtypes")
         Iterator iter = vectorSpace.entrySet().iterator();  
           
         while(iter.hasNext())  
         {  
             @SuppressWarnings("rawtypes")
			Map.Entry entry = (Map.Entry)iter.next();  
             itemCountArray = (int[])entry.getValue();  
               
             vector1Modulo += itemCountArray[0]*itemCountArray[0];  
             vector2Modulo += itemCountArray[1]*itemCountArray[1];  
               
             vectorProduct += itemCountArray[0]*itemCountArray[1];  
         }  
           
	         vector1Modulo = Math.sqrt(vector1Modulo);  
	         vector2Modulo = Math.sqrt(vector2Modulo);  
	           
	         //返回相似度  
	         return (vectorProduct/(vector1Modulo*vector2Modulo)); 

     	}
	//获得最高相似度的专家id
		public String getSimilarity(String keyword,List<Expert> list) {
			double similarity = 0;
			String similarityID = null;
			for(Expert e:list) {
				if(similarity < getSimilarDegree(keyword,e.getResearchname())) {
					similarity = getSimilarDegree(keyword,e.getResearchname());
					similarityID = e.getId();			
				}else if(similarity > maxSimilarity) break;			
			}
			return similarityID;
		}
	
	//全局变量：专家可匹配的最大论文数以及符合匹配条件的相似度(调用字典)
	int maxThesis = 4;
	float maxSimilarity = 0.8f;
//	int maxThesis = Integer.parseInt(DictUtils.getDictValue("专家可匹配的最大论文数", "maxThesis", ""));
//	float maxSimilarity = Float.parseFloat((DictUtils.getDictValue("符合匹配条件的相似度", "maxSimilarity", "")));
	//分配方法，返回为数组
	public String[] fenpei(Distribute distribute) {
		Expert expert = new Expert();
		List<Expert> listExpert = expertService.findList(expert);
		//获取专家表listExpert和论文表listThesis，并将可以分配论文的校内外专家分开，放到listExpert1和listExpert2中
		List<Expert> listExpert1 = Lists.newArrayList();//校内
		List<Expert> listExpert2 = Lists.newArrayList();//校外	
		for(Expert e:listExpert) {
			if(e.getDistributeNum() < maxThesis ) {
				if(e.getExpertType().equals("校内")) {//校内
					listExpert1.add(e);					
				}
				if(e.getExpertType().equals("校外")){//校外	
					listExpert2.add(e);
				}
				
			}
		}
		//str1保存校内专家ID，str2保存校外专家ID
		String str1 = getSimilarity(distribute.getKeywords(),listExpert1);
		String str2 = getSimilarity(distribute.getKeywords(),listExpert2);
		String str[] = new String[3];//保存分配信息
		//当未完成匹配时，str[0]=-1,str[1]保存匹配的错误信息
		//当完成匹配时，str[0]保存论文ID，str[1]保存校内专家ID，str[2]保存校外专家ID
		if(str1 == null ||str2 == null) {//如果未同时匹配到校内外专家
			str[0] = "-1";
			if(str1 == null && str2 != null)
				str[1] = "校内专家匹配失败";
			if(str2 == null && str1 != null)
				str[1] = "校外专家匹配失败";
			if(str1 == null && str2 == null)
				str[1] = "校内专家和校外专家匹配失败";							
		}else {	
			//成功后保存分配信息
			str[0] = distribute.getThesisid();
			str[1] = str1;
			str[2] = str2;
						
		}
//		return "匹配成功";
		return str;
		
	}
	
}
