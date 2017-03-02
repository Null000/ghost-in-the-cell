import java.io.InputStream;
import java.io.PrintStream;
import java.util.Properties;

public abstract class MultiReferee {
    private final InputStream in;
    private final PrintStream out;
    private final PrintStream err;

    public MultiReferee(InputStream in, PrintStream out, PrintStream err) {
        this.in = in;
        this.out = out;
        this.err = err;
    }


    protected String translate(String incAction, int i, int id, int id2) {
        return incAction;
    }

    protected String translate(String incAction, int i, int id) {
        return translate(incAction, i, id, -1);
    }


    protected void addToolTip(int id, String bombAction) {
    }

    protected abstract void initReferee(int playerCount, Properties prop) throws InvalidFormatException;

    protected abstract Properties getConfiguration();

    protected abstract String[] getInitInputForPlayer(int playerIdx);

    protected abstract void prepare(int round);

    protected abstract String[] getInputForPlayer(int round, int playerIdx);

    protected abstract int getExpectedOutputLineCountForPlayer(int playerIdx);

    protected abstract void handlePlayerOutput(int frame, int round, int playerIdx, String[] outputs)
            throws WinException, LostException, InvalidInputException;

    protected abstract void updateGame(int round) throws GameOverException;

    protected abstract void populateMessages(Properties p);

    protected abstract String[] getInitDataForView();

    protected abstract String[] getFrameDataForView(int round, int frame, boolean keyFrame);

    protected abstract String getGameName();

    protected abstract String getHeadlineAtGameStartForConsole();

    protected abstract int getMinimumPlayerCount();

    protected abstract boolean showTooltips();

    protected abstract String[] getPlayerActions(int playerIdx, int round);

    protected abstract boolean isPlayerDead(int playerIdx);

    protected abstract String getDeathReason(int playerIdx);

    protected abstract int getScore(int playerIdx);

    protected abstract String[] getGameSummary(int round);

    protected abstract void setPlayerTimeout(int frame, int round, int playerIdx);

    protected abstract int getMaxRoundCount(int playerCount);

    protected abstract int getMillisTimeForRound();
}
