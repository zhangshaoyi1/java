
 
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
 
/**
 * �ַ���������ƥ���㷨
 * Created by panther on 15-7-20.
 */
public class Similarity {
    Map<Character, int[]> vectorMap = new HashMap<Character, int[]>();
 
    int[] tempArray = null;
 
    public Similarity(String string1, String string2) {
 
        for (Character character1 : string1.toCharArray()) {
            if (vectorMap.containsKey(character1)) {
                vectorMap.get(character1)[0]++;
            } else {
                tempArray = new int[2];
                tempArray[0] = 1;
                tempArray[1] = 0;
                vectorMap.put(character1, tempArray);
            }
        }
        for (Character character2 : string2.toCharArray()) {
            if (vectorMap.containsKey(character2)) {
                vectorMap.get(character2)[1]++;
            } else {
                tempArray = new int[2];
                tempArray[0] = 0;
                tempArray[1] = 1;
                vectorMap.put(character2, tempArray);
            }
        }
    }
 
    // ���������ƶ�
    public double sim() {
        double result = 0;
        result = pointMulti(vectorMap) / sqrtMulti(vectorMap);
        return result;
    }
 
    private double sqrtMulti(Map<Character, int[]> paramMap) {
        double result = 0;
        result = squares(paramMap);
        result = Math.sqrt(result);
        return result;
    }
 
    // ��ƽ����
    private double squares(Map<Character, int[]> paramMap) {
        double result1 = 0;
        double result2 = 0;
        Set<Character> keySet = paramMap.keySet();
        for (Character character : keySet) {
            int temp[] = paramMap.get(character);
            result1 += (temp[0] * temp[0]);
            result2 += (temp[1] * temp[1]);
        }
        return result1 * result2;
    }
 
    // ��˷�
    private double pointMulti(Map<Character, int[]> paramMap) {
        double result = 0;
        Set<Character> keySet = paramMap.keySet();
        for (Character character : keySet) {
            int temp[] = paramMap.get(character);
            result += (temp[0] * temp[1]);
        }
        return result;
    }
 
    public static void main(String[] args) {
        String s1 = "ţ��";
        String s2 = "��ţ";
        Similarity similarity = new Similarity(getText(s1), getText(s2));
        System.out.println(similarity.sim());
    }
    public static String getText(String str){
    	char[] arr = str.toCharArray();
    	String str1 = null;
    	for (int i = 0; i < arr.length; i++) {
			if(arr[i] != '��' && arr[i] != ',' && arr[i] != ' ') {
				str1 += arr[i];
			}
				
			}
		
    	return str1;
    }
 
}
