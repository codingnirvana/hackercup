package wordracer;

public interface IWordRacer
{
    public void initGameBoard(char letter);
    public DemoWordRacer.Result pickLetter();
    public int pickPosition(char letter);
}
