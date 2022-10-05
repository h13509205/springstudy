package com.example.demo.spel;

import com.example.demo.fsm.TransferContext;
import com.example.demo.fsm.rule.*;
import com.example.demo.mybatis.po.Password;
import com.example.demo.mybatis.po.User;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import java.util.*;

public class SpelStudy {

    private static ExpressionParser parser = new SpelExpressionParser();

    public static void main(String[] args) {
        //literal();
        //field();
        //array();
        //map();
        //inlineList();
        //inlineMap();
        //listSelect();
        mapSelect();
    }

    //支持字符串，数值，bollean，null，其中用于比较的话，null最小
    public static void literal(){
        //String
        System.out.println(parser.parseExpression("'test String'").getValue(String.class));

        //数值
        System.out.println(parser.parseExpression("-1").getValue(double.class));
        System.out.println(parser.parseExpression("-1").getValue(int.class));
        System.out.println(parser.parseExpression("132.4523423E+10").getValue(double.class));
        System.out.println(parser.parseExpression("0x7FFFFFFF").getValue(int.class));
        // 先转int System.out.println(parser.parseExpression("0x7FFFFFFFFF").getValue(long.class));
        // 无法解析8进制 System.out.println(parser.parseExpression("01001").getValue(int.class));
        // 无法解析二进制 System.out.println(parser.parseExpression("0b1001").getValue(int.class));

        //boolean
        System.out.println(parser.parseExpression("true").getValue(boolean.class));

        //null 可以用任何对象接，反正是空对象
        TransferContext nullValue = parser.parseExpression("null").getValue(TransferContext.class);
        System.out.println(nullValue);
    }
    //获取用户属性
    public static void field(){
        User user = new User();
        user.setId(1);
        user.setUserName("hwt");
        user.setPassword(new Password("123456"));
        System.out.println(parser.parseExpression("password.password").getValue(user, String.class));
    }

    public static void array(){
        //直接的数组访问
        int[] array = new int[]{1,2,3,4,6,7};
        System.out.println(parser.parseExpression("[4]").getValue(array));

        //访问对象中的list
        TransferContext transferContext = new TransferContext();
        LinkedList<IRule> list = new LinkedList<>();
        list.add(new RuleWapper(new StartRule()));
        list.add(new RuleWapper(new ReinforceRule()));
        list.add(new RuleWapper(new RemindRule()));
        transferContext.setRuleList(list);
        System.out.println(parser.parseExpression("ruleList[2].toString()").getValue(transferContext));
    }

    public static void map(){
        Map<String,Integer> map = new HashMap<>();
        map.put("aaa", 111);
        map.put("bbb", 222);
        map.put("Ccc", 333);
        System.out.println(parser.parseExpression("['aaa']").getValue(map));
        System.out.println(parser.parseExpression("['ccc']").getValue(map));//返回空对象
    }

    public static void inlineList(){
        // 返回的还是int格式的 List<String> members1 = (List<String>) parser.parseExpression("{1,2,3,4}").getValue();
        List<Integer> members2 = (List<Integer>) parser.parseExpression("{1,2,3,4}").getValue();
        System.out.println(members2);

        //返回的还是int格式 List<Float> members3 = (List<Float>) parser.parseExpression("{1,2,3,4}").getValue();
        // 可以用这种方式打印 System.out.println(members3);
        // 拿出来用的时候，报强转错误 members3.forEach(System.out::println);
        List<String> members1 = (List<String>) parser.parseExpression("{'1','2','3','4'}").getValue();
        System.out.println(members1);

        List<List<Integer>> listOfList = (List<List<Integer>>) parser.parseExpression("{{1,2,3,4},{1,2,3,4}}").getValue();
        System.out.println(listOfList);
    }

    public static void inlineMap(){
        Map<String, Object> map = (Map<String, Object>) parser
                .parseExpression("{'name':'hwt','password':123456, 'gender':true}").getValue();
        System.out.println(map);

        Map<String, Object> mapOfMap = (Map<String, Object>) parser
                .parseExpression("{'name':'hwt','other':{'password':123456,'gender':true}}").getValue();
        System.out.println(mapOfMap);

        Map<String, Object> mapOfList = (Map<String, Object>) parser
                .parseExpression("{'name':'hwt','other':{1,2,3,4,7,8}}").getValue();
        System.out.println(mapOfList);

        List<Map<String, Object>> listOfMap = (List<Map<String, Object>>) parser
                .parseExpression("{{'name':'hwt'},{'name':'wsy'}}").getValue();
        System.out.println(listOfMap);
    }

    public static void listSelect(){
        List<Inventor> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(new Inventor(i, "发明家"+i, i%2 ==0 ? "中国":"外国"));
        }
        //筛选所有
        List<Inventor> newList1 = (List<Inventor>) parser
                .parseExpression("?[nation == '中国']").getValue(list);
        newList1.forEach(System.out::println);

        System.out.println();
        //筛选第一个
        Inventor newInventor1 = (Inventor) parser
                .parseExpression("^[nation == '中国']").getValue(list);
        System.out.println(newInventor1);
        //筛选最后一个
        Inventor newInventor2 = (Inventor) parser
                .parseExpression("$[nation == '中国']").getValue(list);
        System.out.println(newInventor2);
    }

    public static void mapSelect(){
        Map<Integer, Integer> hashMap = new HashMap<>();
        hashMap.put(1,1);
        hashMap.put(2,2);
        hashMap.put(5,4);
        hashMap.put(4,5);

        Map<Integer, Integer> newMap1 = (Map<Integer, Integer>) parser
                .parseExpression("?[value>2]").getValue(hashMap);
        System.out.println(newMap1);

        Map<Integer, Integer> newMap2 = (Map<Integer, Integer>) parser
                .parseExpression("?[key>=2]").getValue(hashMap);
        System.out.println(newMap2);

        Map<Integer, Integer> newMap3 = (Map<Integer, Integer>) parser
                .parseExpression("^[key>=2]").getValue(hashMap);
        System.out.println(newMap3);

        Map<Integer, Integer> newMap4 = (Map<Integer, Integer>) parser
                .parseExpression("$[key>=2]").getValue(hashMap);
        System.out.println(newMap4);

        Map<Integer, Integer> treeMap = new TreeMap<>();
        treeMap.put(1,1);
        treeMap.put(2,2);
        treeMap.put(5,4);
        treeMap.put(4,5);
        treeMap.put(3,6);

        //匹配最后一个，按遍历的顺序，treemap则是按照key顺序排序
        Map<Integer, Integer> newMap5 = (Map<Integer, Integer>) parser
                .parseExpression("$[value>=2]").getValue(treeMap);
        System.out.println(newMap5);
        treeMap.entrySet().stream().forEach(System.out::println);
    }
}


class Inventor{
    Inventor(int id, String name, String nation){
        this.id = id;
        this.name = name;
        this.nation = nation;
    }

    private int id;
    private String name;

    private String nation;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Inventor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", nation='" + nation + '\'' +
                '}';
    }
}