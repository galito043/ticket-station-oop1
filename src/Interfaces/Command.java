package Interfaces;

public interface Command <T, S>{
    T run(S[] args) throws Exception;
}
