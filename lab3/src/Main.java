
public class Main {

    public static void main(String[] args) {
        String[] formulae = {"-a V b", "-b", "-a", "a"};
        
        Engine theEngine = new Engine(formulae);
        theEngine.resolve();

    }
	
}
