package scan;


public class LinearScan implements ScanInterface
{
	public int c(int hk, int i, int n)
	{
		return (hk + i) % n;
	}
}