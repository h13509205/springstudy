package com.example.demo.spel;

import com.example.demo.fsm.TransferContext;
import com.example.demo.fsm.rule.*;
import com.example.demo.mybatis.po.Password;
import com.example.demo.mybatis.po.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.expression.BeanFactoryResolver;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.SimpleEvaluationContext;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

@Configuration
@ComponentScan
public class SpelStudy {

    private static ExpressionParser parser = new SpelExpressionParser();

    public static String publicStaticString = "public static String";
    private static String privateStaticString = "private static String";
    protected static String protectedStaticString = "protected static String";

    @Bean("startRule")
    private RuleWapper getBean(){
        return new RuleWapper(new StartRule());
    }

    public static void main(String[] args) throws NoSuchMethodException {
        //literal();
        //field();
        //array();
        //map();
        //inlineList();
        //inlineMap();
        //listSelect();
        //mapSelect();
        //collectionMapping();
        //arrayInit();
        //relationalOperator();
        //logicalOperator();
        //mathmaticalOperator();
        //assign();
        //safeVisitor();
        //functionInvoke();
        //specialT();
        //expressionLanguangeTemplate();
        //varialbes();
        //thisAndRoot();
        //customizeFunction();
        //getSpringBean();
        parameterInjection();
    }

    public static boolean test1(Integer integer){
        return integer%2 ==0;
    }

    protected static boolean test2(Integer integer){
        return integer%3 ==0;
    }

    private static boolean test3(Integer integer){
        return integer%5 ==0;
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
        //支持多级访问
        System.out.println(parser.parseExpression("password.password.getBytes().length").getValue(user, String.class));
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


    public static void collectionMapping(){
        List<Inventor> list = new ArrayList<>();
        Map<Integer, Inventor> map = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            list.add(new Inventor(i, "发明家"+i, i%2 ==0 ? "中国":"外国"));
            map.put(i, list.get(i));
        }
        //List<Object> 转 List<String>
        List<String> listString1 = (List<String>) parser.parseExpression("![name]").getValue(list);
        System.out.println(listString1);

        //Map<Object> 转 List<String>
        List<String> listString2 = (List<String>) parser.parseExpression("![key+'-'+value.name]").getValue(map);
        System.out.println(listString2);
    }

    public static void arrayInit(){
        int[] array1 = (int[]) parser.parseExpression("new int[4]").getValue();
        int[] array2 = (int[]) parser.parseExpression("new int[4]{1,2,3,4}").getValue();
        int[][] doubleArray1 = (int[][]) parser.parseExpression("new int[4][4]").getValue();
        //二位数组无法初始化  int[][] doubleArray2 = (int[][]) parser.parseExpression("new int[2][2]{{1,2},{3,4}}").getValue();
        System.out.println(Arrays.toString(array1));
        System.out.println(Arrays.toString(array2));
        System.out.println(Arrays.toString(doubleArray1));
    }

    public static void relationalOperator(){
        // > < >= <= == !=
        //true
        System.out.println(parser.parseExpression("1<2").getValue());
        //false
        System.out.println(parser.parseExpression("1>2").getValue());
        //true
        System.out.println(parser.parseExpression("2>=-2").getValue());
        //true
        System.out.println(parser.parseExpression("'asdvhgas'<='trytdsf'").getValue());
        //false
        System.out.println(parser.parseExpression("'2'==2").getValue());
        //true
        System.out.println(parser.parseExpression("'2'!=2").getValue());

        //instance of  数字会立即装箱，从int变成Integer
        Inventor inventor = new Inventor(1,"hwt","中国");
        System.out.println(parser.parseExpression("1 instanceof T(int)").getValue());
        System.out.println(parser.parseExpression("1 instanceof T(Integer)").getValue());
        System.out.println(parser.parseExpression("'132432' instanceof T(String)").getValue());
        System.out.println(parser.parseExpression("true instanceof T(Boolean)").getValue());
        //看起来不能这样搞，只能用String和Integer和boolean  System.out.println(parser.parseExpression("instanceof T(Integer)").getValue(inventor));

        //matches 正则表达式
        //true
        System.out.println(parser.parseExpression("'8.8.8.8' matches '\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}'").getValue());
        //false
        System.out.println(parser.parseExpression("'8a.8.8.8' matches '\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}'").getValue());
    }

    // && || !
    public static void logicalOperator(){
        //false
        System.out.println(parser.parseExpression("true && false").getValue());
        //true
        System.out.println(parser.parseExpression("true || false").getValue());
        //false
        System.out.println(parser.parseExpression("!true").getValue());
        //程序异常，不能转化       System.out.println(parser.parseExpression("!1").getValue());
    }

    //+ - * / mod
    public static void mathmaticalOperator(){
        //+ String作用在String上的只能用+
        System.out.println(parser.parseExpression("'hwt'+'-'+'wsy'").getValue());
        System.out.println(parser.parseExpression("'hwt'+1").getValue());
        //报错，EL1030E: The operator 'SUBTRACT' is not supported between objects of type 'java.lang.String' and 'java.lang.Integer'
        // System.out.println(parser.parseExpression("'hwt'-1").getValue());
        //2.9411764705882355 看精度应该是double
        System.out.println(parser.parseExpression("100.0/34").getValue());
        //2.9411764
        System.out.println(parser.parseExpression("100.0/34").getValue(float.class));
        //2 默认转化成了int
        System.out.println(parser.parseExpression("100/34").getValue());
        //2.94...  实际是2.0
        System.out.println(parser.parseExpression("100/34").getValue(float.class));
        //32 mod
        System.out.println(parser.parseExpression("100%34").getValue());
        //-13 复杂计算
        System.out.println(parser.parseExpression("1-2*(3+4)").getValue());
    }

    // = 赋值   三目运算与 Elvis操作符
    public static void assign(){
        // =
        Inventor inventor = new Inventor(1, "hwt", "中国");
        System.out.println("before assign: " + inventor);
        parser.parseExpression("name = 'wsy'").getValue(inventor);
        System.out.println("after assign: " + inventor);

        //普通三目运算
        inventor.setName(null);
        String name = (String) parser.parseExpression("name != null ? name : 'null name'").getValue(inventor);
        System.out.println(name);

        //Elvis简化三目运算
        name = (String) parser.parseExpression("name ?: 'null Elvis name'").getValue(inventor);
        System.out.println(name);
    }

    //嵌套属性安全访问 ?
    public static void safeVisitor(){
        //normal visitor
        User user1 = new User(1, "hwt", new Password("123456"));
        System.out.println(parser.parseExpression("password.password").getValue(user1, String.class));
        System.out.println(parser.parseExpression("password?.password").getValue(user1, String.class));
        //safe visitor
        User user2 = new User(1, "hwt", null);
        System.out.println(parser.parseExpression("password?.password").getValue(user2, String.class));

        //exception System.out.println(parser.parseExpression("password.password").getValue(user2, String.class));
    }

    //方法调用
    public static void functionInvoke(){
        //normal function
        System.out.println(parser.parseExpression("'abcde'.substring(1,3)").getValue());
        System.out.println(Arrays.toString((String[])parser.parseExpression("'ab cd e'.split(' ')").getValue()));
        Inventor inventor = new Inventor(1, "hwt", "中国");
        System.out.println(parser.parseExpression("invokeMyself(43675)").getValue(inventor));

        //constructor 除原始类型（int、float 等）和 String 之外的所有类型使用完全限定的类名
        // 这句可以执行 Inventor inventor2 = new com.example.demo.spel.Inventor(1,"hwt","中国");
        // 这句不能执行 不知道为什么，说是找不到合适的constructor Inventor inventor2 = parser.parseExpression("new com.example.demo.spel.Inventor(1,'hwt','中国')").getValue(Inventor.class);
        //System.out.println(inventor2);
        RuleWapper ruleWapper = parser.parseExpression("new com.example.demo.fsm.rule.RuleWapper(new com.example.demo.fsm.rule.StartRule())").getValue(RuleWapper.class);
        System.out.println(ruleWapper);
    }

    // T 指的是java.lang.Class 这个专门的类
    public static void specialT(){
        // java.lang下的类可以不使用全限定名
        Class clazz1 = parser.parseExpression("T(String)").getValue(Class.class);
        System.out.println(clazz1);
        //仅限刚好在java.lang下第一层能找到的包，java.lang.reflect下的就不行 Class clazz2 = parser.parseExpression("T(Field)").getValue(Class.class);
        Class clazz2 = parser.parseExpression("T(Exception)").getValue(Class.class);
        System.out.println(clazz2);

        //获取其他类需要用全限定名
        Class clazz3 = parser.parseExpression("T(com.example.demo.spel.Inventor)").getValue(Class.class);
        System.out.println(clazz3);

        //获取静态属性，只能访问public的属性，说明也不是通过反射去实现的
        //public
        String s1 = parser.parseExpression("T(com.example.demo.spel.SpelStudy).publicStaticString").getValue(String.class);
        System.out.println(s1);
        //protect L1008E: Property or field 'protectStaticString' cannot be found on object of type 'com.example.demo.spel.SpelStudy' - maybe not public or not valid?
        //String s2 = parser.parseExpression("T(com.example.demo.spel.SpelStudy).protectStaticString").getValue(String.class);
        //private String s3 = parser.parseExpression("T(com.example.demo.spel.SpelStudy).privateStaticString").getValue(String.class);

        //调用静态方法,应该也是只能访问public的
        //返回是void
        System.out.println();
        parser.parseExpression("T(com.example.demo.spel.SpelStudy).safeVisitor()").getValue();
        System.out.println();
        //返回是具体的类型
        Boolean b1 = parser.parseExpression("T(com.example.demo.spel.SpelStudy).test1(100)").getValue(Boolean.class);
        System.out.println(b1);

    }

    //表达式模板，需要使用TemplateParserContext，这个TemplateParserContext只将#{}中的东西当做spel进行解析，在外面的就当字符串处理，如果用默认的解析器做解析，那么就把所有的字符串当做spel进行解析（包括#{}外面的字符串）
    public static void expressionLanguangeTemplate(){
        String a = parser.parseExpression("this is #{T(com.example.demo.spel.SpelStudy).test1(100)}", new TemplateParserContext()).getValue(String.class);
        System.out.println(a);
    }

    //获取参数
    public static void varialbes(){
        //StandardEvaluationContext
        Inventor inventor = new Inventor(1, "hwt", "中国");
        EvaluationContext context  = new StandardEvaluationContext(inventor);
        context.setVariable("map", "my map");
        parser.parseExpression("name = #map").getValue(context, inventor);
        //parser.parseExpression("name = 'map'").getValue(inventor);
        System.out.println(inventor);

        //SimpleEvaluationContext  如果使用SimpleEvaluationContext.forReadOnlyDataBinding()，则不能进行赋值操作
        Inventor inventor2 = new Inventor(1, "hwt", "中国");
        EvaluationContext context2 = SimpleEvaluationContext.forReadWriteDataBinding().build();
        context2.setVariable("map", "my map2");
        parser.parseExpression("name = #map").getValue(context2, inventor2);
        System.out.println(inventor2);
    }

    //#this变量永远指向当前表达式正在求值的对象（这时不需要限定全名）。变量#root总是指向根上下文对象。#this在表达式不同部分解析过程中可能会改变，但是#root总是指向根
    public static void thisAndRoot(){
        List<Integer> primes = new ArrayList<>(Arrays.asList(2, 3, 5, 7, 11, 13, 17));
        List<Integer> evens = new ArrayList<>(Arrays.asList(2, 4, 6, 8, 10, 12, 14));
        StandardEvaluationContext context = new StandardEvaluationContext();
        context.setVariable("primes", primes);
        context.setVariable("evens", evens);
        List<Integer> primesGraterThanTen = (List<Integer>) parser.parseExpression("#primes.?[#this>10]").getValue(context);
        System.out.println(primesGraterThanTen);
        List<Integer> evensGraterThanTen = (List<Integer>) parser.parseExpression("#evens.?[#this>10]").getValue(context);
        System.out.println(evensGraterThanTen);
    }

    public static void customizeFunction() throws NoSuchMethodException {
        Method method = StringUtils.class.getDeclaredMethod("hasLength", String.class);
        //注册到变量里
        StandardEvaluationContext context1 = new StandardEvaluationContext();
        context1.setVariable("hasLength", method);
        System.out.println(parser.parseExpression("#hasLength('124312')").getValue(context1));
        System.out.println(parser.parseExpression("#hasLength(null)").getValue(context1));
        System.out.println();
        //注册到context的方法里，调用方式还是和变量一样
        StandardEvaluationContext context2 = new StandardEvaluationContext();
        context2.registerFunction("hasLength", method);
        System.out.println(parser.parseExpression("#hasLength('124312')").getValue(context2));
        System.out.println(parser.parseExpression("#hasLength(null)").getValue(context2));
    }

    //使用@来获取Bean
    // 跑不起来，在linux上估计行  java.nio.file.InvalidPathException: Illegal char <:> at index 2: /C:/Users/hwt20/IdeaProjects/springstudy/target/classes/com/example/demo/spel/
    public static void getSpringBean(){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpelStudy.class);
        StandardEvaluationContext context = new StandardEvaluationContext();
        context.setBeanResolver(new BeanFactoryResolver(applicationContext));
        RuleWapper ruleWapper = (RuleWapper) parser.parseExpression("@startRule").getValue(context);
        System.out.println(ruleWapper);
    }

    //使用spel注入参数，分xml和@Value注入，配置均相同，现在就只看@Value注入
    //内置对象：标准上下文环境 environment，类型为 org.springframework.core.env.Environment
    //内置对象：JVM系统属性systemProperties，类型为 Map<String, Object>
    //内置对象：系统环境变量systemEnvironment，类型为 Map<String, Object>
    public static void parameterInjection(){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext("com.example");
        TestParameterInjectionComponent component = applicationContext.getBean(TestParameterInjectionComponent.class);
        System.out.println(component);
    }
}


class Inventor implements IRule{
    Inventor(Integer id, String name, String nation){
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

    public Inventor invokeMyself(int i){
        Random r = new Random();
        id = r.nextInt() + i;
        System.out.println("i = "+i);
        System.out.println("random key = "+ (id-i));
        return this;
    }
}