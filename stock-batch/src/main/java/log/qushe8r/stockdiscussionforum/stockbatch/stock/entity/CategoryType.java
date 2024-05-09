package log.qushe8r.stockdiscussionforum.stockbatch.stock.entity;

public enum CategoryType {
    KOSPI, KOSDAQ;

    public static final CategoryType[] CATEGORY_TYPES = CategoryType.values();

    public CategoryType next() {
        return CATEGORY_TYPES[(this.ordinal() + 1) % CATEGORY_TYPES.length];
    }

}
