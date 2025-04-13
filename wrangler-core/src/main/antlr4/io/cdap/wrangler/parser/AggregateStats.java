@Directive(name = "aggregate-stats", type = Directive.Type.AGGREGATE)
public class AggregateStats implements Directive {
    private String sizeColumn;
    private String timeColumn;
    private String totalSizeOutput;
    private String totalTimeOutput;

    private long totalBytes = 0;
    private long totalMilliseconds = 0;

    @Override
    public UsageDefinition define() {
        return UsageDefinition.builder()
            .define("sizeColumn", TokenType.COLUMN_NAME)
            .define("timeColumn", TokenType.COLUMN_NAME)
            .define("totalSizeOutput", TokenType.COLUMN_NAME)
            .define("totalTimeOutput", TokenType.COLUMN_NAME)
            .build();
    }

    @Override
    public void initialize(Arguments args) {
        sizeColumn = ((ColumnName) args.value("sizeColumn")).value();
        timeColumn = ((ColumnName) args.value("timeColumn")).value();
        totalSizeOutput = ((ColumnName) args.value("totalSizeOutput")).value();
        totalTimeOutput = ((ColumnName) args.value("totalTimeOutput")).value();
    }

    @Override
    public List<Row> execute(List<Row> rows, ExecutorContext ctx) {
        for (Row row : rows) {
            Object sizeValue = row.getValue(sizeColumn);
            Object timeValue = row.getValue(timeColumn);

            ByteSize size = new ByteSize(sizeValue.toString());
            TimeDuration time = new TimeDuration(timeValue.toString());

            totalBytes += size.getBytes();
            totalMilliseconds += time.getMilliseconds();
        }

        Row result = new Row();
        result.add(totalSizeOutput, (double) totalBytes / (1024 * 1024)); // MB
        result.add(totalTimeOutput, (double) totalMilliseconds / 1000);  // seconds

        return Collections.singletonList(result);
    }
}
