
public class Main {

    public static void main(String[] args) {
        String[] formulae = {"-a V a", "a", "-b"};
        
        Engine theEngine = new Engine(formulae);
        theEngine.resolve();

    }
	
}
