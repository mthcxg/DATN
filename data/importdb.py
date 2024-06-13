import csv

# Tên file CSV
csv_file = "data/imdb-movies-dataset.csv" 

# Các cột cần lấy từ file CSV
columns_to_import = [
    "Poster",
    "Title", 
    "Year",
    "Certificate",
    "Rating",
    "Metascore",
    "Director",
    "Votes",
    "Description"
]

# Tạo câu lệnh SQL
sql_template = """
INSERT INTO `movies`(`id`, `description`, `name`, `num_rate`, `rate`, `release_year`, `image`, `director`, `certificate`, `metascore`) VALUES (
    '',
    "{description}",
    "{title}",
    "{votes}",
    ROUND({rating}/2, 2),
    "{year}",
    "{poster}",
    "{director}",
    "{certificate}",
    "{metascore}"
);
"""

# Tên file để lưu các câu lệnh SQL
output_file = "movies.sql"

# Đọc dữ liệu từ file CSV và ghi vào file SQL
with open(csv_file, "r", encoding="utf-8") as f_csv, open(output_file, "w") as f_sql:
    reader = csv.DictReader(f_csv)
    for row in reader:
        row_values = {
            "description": row["Description"].replace('"', '""'),
            "title": row["Title"],
            "year": row["Year"],
            "certificate": row["Certificate"],
            "rating": row["Rating"],
            "metascore": row["Metascore"],
            "director": row["Director"],
            "votes": row["Votes"].replace(',', ''),
            "poster": row["Poster"]
        }
        sql_query = sql_template.format(**row_values)
        f_sql.write(sql_query + "\n")

genre_file = "genres.csv" 

# Các cột cần lấy từ file CSV
columns_to_import = [
    "Genre"
]

# Tạo câu lệnh SQL
sql_template = """
INSERT INTO `category`(`id`, `name`) VALUES (
    '',
    "{genre}"
);
"""

# Tên file để lưu các câu lệnh SQL
output_file = "genre.sql"

# Đọc dữ liệu từ file CSV và ghi vào file SQL
with open(genre_file, "r", encoding="utf-8") as f_csv, open(output_file, "w") as f_sql:
    reader = csv.DictReader(f_csv)
    for row in reader:
        row_values = {
            "genre": row["Genre"],
        }
        sql_query = sql_template.format(**row_values)
        f_sql.write(sql_query + "\n")

actor_file = "actors.csv" 

# Các cột cần lấy từ file CSV
columns_to_import = [
    "Cast"
]

# Tạo câu lệnh SQL
sql_template = """
INSERT INTO `actors`(`id`, `name`) VALUES (
    '',
    "{actor}"
);
"""

# Tên file để lưu các câu lệnh SQL
output_file = "actors.sql"

# Đọc dữ liệu từ file CSV và ghi vào file SQL
with open(actor_file, "r", encoding="utf-8") as f_csv, open(output_file, "w") as f_sql:
    reader = csv.DictReader(f_csv)
    for row in reader:
        row_values = {
            "actor": row["Cast"],
        }
        sql_query = sql_template.format(**row_values)
        f_sql.write(sql_query + "\n")


