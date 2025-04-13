🚀 New Features: ByteSize and TimeDuration Parsers
📌 Overview
This enhancement adds native support for parsing ByteSize (e.g., 10KB, 1.5MB) and TimeDuration (e.g., 5ms, 2s) values in Wrangler recipes. It introduces new token types and a directive called aggregate-stats to perform aggregations on these units.

🧠 New Token Types
Token Type	Description	Example Values
BYTE_SIZE	Represents size values with units	10KB, 1.5MB, 2GB
TIME_DURATION	Represents time durations with units	150ms, 2s, 3min
📘 New Directive: aggregate-stats
This directive allows aggregation (sum or average) of columns containing byte sizes and time durations.

Usage:
wrangler
Copy
Edit
aggregate-stats :data_transfer_size :response_time total_size_mb total_time_sec
Arguments:
data_transfer_size – Source column with ByteSize values

response_time – Source column with TimeDuration values

total_size_mb – Target column name for total size (converted to MB)

total_time_sec – Target column name for total time (converted to seconds)

🧪 Testing
You can run the unit tests using:

bash
Copy
Edit
mvn clean test
Tests validate:

ByteSize and TimeDuration token parsing

Correct aggregate calculations in the aggregate-stats directive

