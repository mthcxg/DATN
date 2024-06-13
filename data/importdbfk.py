import pandas as pd

# Đọc dữ liệu từ file CSV vào DataFrame
df = pd.read_csv('new_movie_categories.csv')

with open('insert_movie_categories.sql', 'w') as f:
    # Duyệt qua từng dòng trong DataFrame và ghi câu lệnh SQL vào file
    for index, row in df.iterrows():
        title = row['Title']
        genre_ids = str(row['Genre_id']).split(',')

        # Lấy movieId từ bảng movies
        movie_id_query = "(SELECT id FROM movies WHERE name = '{}' LIMIT 1)".format(title.replace("'", "''"))

        # Insert các categoryId vào bảng movie_categories
        for genre_id in genre_ids:
            insert_query = "INSERT INTO movie_categories (movieId, categoryId) VALUES ({}, {})".format(movie_id_query, genre_id.strip())
            f.write(insert_query + ";\n")
        f.write("\n")

df = pd.read_csv('new_movie_actors.csv')

with open('insert_movie_actors.sql', 'w') as f:
    # Duyệt qua từng dòng trong DataFrame và ghi câu lệnh SQL vào file
    for index, row in df.iterrows():
        title = row['Title']
        genre_ids = str(row['Actor_id']).split(',')

        # Lấy movieId từ bảng movies
        movie_id_query = "(SELECT id FROM movies WHERE name = '{}' LIMIT 1)".format(title.replace("'", "''"))

        # Insert các categoryId vào bảng movie_categories
        for genre_id in genre_ids:
            insert_query = "INSERT INTO movie_actors (movieId, actorId) VALUES ({}, {})".format(movie_id_query, genre_id.strip())
            f.write(insert_query + ";\n")
        f.write("\n")