
public class Main {

    public static void main(String[] args) {
        String[] formulae = {"-a V b", "c V d", "a V c V d"};
        
        Engine theEngine = new Engine(formulae);
        theEngine.resolve();
        
    }
	
}
