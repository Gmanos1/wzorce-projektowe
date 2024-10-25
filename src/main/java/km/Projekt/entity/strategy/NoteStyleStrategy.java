package km.Projekt.entity.strategy;

public interface NoteStyleStrategy {
    String applyStyle(String noteContent); //stosowanie odpowiedniego stylu dla treści notatki
}

/*
 * NoteStyleStrategy.java - interfejs, metoda wspólna dla reszty
 * BasicStyle.java i PriorityStyle.java - klasy implementujące style notatek
 * NoteStyle.java - dynamiczne przypisanie stylu; dowolna implementacja stylu
 * NoteStyleFactory.java - wywołanie obiektów klasy NoteStyle
 */