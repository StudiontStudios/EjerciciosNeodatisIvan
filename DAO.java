package neodatis;

public interface DAO {
    public abstract boolean select();
    public abstract boolean delete();
    public abstract boolean update();
    public abstract boolean insert();
}
