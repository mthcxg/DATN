import pandas as pd

# Đọc dữ liệu từ file imdb-movies-dataset.csv
df = pd.read_csv('imdb-movies-dataset.csv')

# Lưu cột actor và Title vào file mới
df[['Title', 'Cast']].to_csv('movie_actors.csv', index=False)

movies_df = pd.read_csv('movie_actors.csv')

# Đọc dữ liệu từ file actors.csv
actors_df = pd.read_csv('actors.csv')

# Tạo một DataFrame mới để lưu kết quả
result_df = pd.DataFrame(columns=['Title', 'Actor_id'])

# Duyệt qua từng hàng trong file movie_actors.csv
for index, row in movies_df.iterrows():
    title = row['Title']
    try:
        actors = row['Cast'].split(', ')
    except:
        print(f"Error splitting 'Cast' column for movie '{title}'. Skipping this row.")
        continue
    
    # Duyệt qua từng diễn viên của phim
    for actor in actors:
        # Tìm id của diễn viên trong file actors.csv
        actor_id = actors_df.loc[actors_df['Cast'] == actor, 'id'].values[0]
        
        # Kiểm tra xem dòng này đã tồn tại trong result_df không
        if not result_df[(result_df['Title'] == title) & (result_df['Actor_id'] == actor_id)].empty:
            continue
        
        # Thêm dòng vào DataFrame kết quả
        result_df = pd.concat([result_df, pd.DataFrame({'Title': [title], 'Actor_id': [actor_id]})])

# Lưu DataFrame kết quả vào file CSV mới
result_df.to_csv('new_movie_actors.csv', index=False)