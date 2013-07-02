package hash;


public class HashDivision implements HashInterface
{
	public int h(Comparable k, int n)
	{
		return Math.abs(k.hashCode()) % n;
	}
}