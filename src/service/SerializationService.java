package service;

public interface SerializationService {
    public abstract void save(String path,Object object);
    public abstract Object read(String path);
}
