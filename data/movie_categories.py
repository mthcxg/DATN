import pandas as pd

# Đọc dữ liệu từ file movie_categories.csv
df = pd.read_csv('imdb-movies-dataset.csv')

# Lưu cột Genre và Title vào file mới
df[['Title', 'Genre']].to_csv('movie_categories.csv', index=False)

movies_df = pd.read_csv('movie_categories.csv')

# Đọc dữ liệu từ file genres.csv
genres_df = pd.read_csv('genres.csv')

# Tạo một DataFrame mới để lưu kết quả
result_df = pd.DataFrame(columns=['Title', 'Genre_id'])

# Duyệt qua từng hàng trong file movie_categories.csv
for index, row in movies_df.iterrows():
    title = row['Title']
    genres = row['Genre'].split(', ')
    
    # Duyệt qua từng thể loại của phim
    for genre in genres:
        # Tìm id của thể loại trong file genres.csv
        genre_id = genres_df.loc[genres_df['Genre'] == genre, 'id'].values[0]
        
        # Thêm dòng vào DataFrame kết quả
        result_df = pd.concat([result_df, pd.DataFrame({'Title': [title], 'Genre_id': [genre_id]})])

# Lưu DataFrame kết quả vào file CSV mới
result_df.to_csv('new_movie_categories.csv', index=False)