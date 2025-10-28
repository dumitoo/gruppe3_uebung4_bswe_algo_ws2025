package at.hochschule.burgenland.bswe.algo.importer;

import java.util.List;

public interface Importable<T> {
    List<T> importData();
}