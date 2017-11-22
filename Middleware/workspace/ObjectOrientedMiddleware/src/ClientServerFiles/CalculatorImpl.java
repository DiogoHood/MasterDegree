package ClientServerFiles;

public class CalculatorImpl implements ICalculator {

	@Override
	public float add(float a, float b) throws Throwable{
		return a+b;
	}
	
	@Override
	public float sub(float a, float b) throws Throwable{
		return a-b;
	}
	
	@Override
	public float div(float a, float b) throws Throwable{
		return a/b;
	}
	
	@Override
	public float mul(float a, float b) throws Throwable{
		return a*b;
	}

}
