package listeners;

public interface WorldMapListener {
    void onManualAttackChosen() throws InterruptedException;
    void onAutoResolveChosen() throws InterruptedException;
}
