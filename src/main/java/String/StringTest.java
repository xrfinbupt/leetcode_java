package String;

public class StringTest {
    public static void main(String args[]){
        System.out.println("hello");
        String filterList = "123,abc,";
        filterList = filterList.substring(0, filterList.length() - 1);
        System.out.println(filterList);
    }
}
