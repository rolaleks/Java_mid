public class TestClass {


    @Test (priotity = 1)
    public void test1(){
        System.out.println("Test 1");
    }

    @Test
    public void test2(){
        System.out.println("Test 2");
    }

    @Test (priotity = 5)
    public void test3(){
        System.out.println("Test 3");
    }

    @AfterSuite
    public void after(){
        System.out.println("Test END");
    }

    @BeforeSuite
    public void start(){
        System.out.println("Test START");
    }
}
