import pandas as pd

df = pd.read_csv('imdb-movies-dataset.csv')
df = df.dropna(subset=['Poster', 'Title', 'Year', 'Certificate', 'Rating', 'Metascore', 'Director', 'Votes', 'Description'])
df.to_csv('imdb-movies-dataset.csv', index=False)
genres = df['Genre'].str.split(',').explode().tolist()

genre_df = pd.DataFrame({'Genre': list(set(genres))})

genre_df.to_csv('genres.csv', index=False)

genre_df = pd.read_csv('genres.csv')

genre_df['Genre'] = genre_df['Genre'].str.strip()

genre_df = genre_df.drop_duplicates()

genre_df.to_csv('genres.csv', index=False)
genre_df = pd.read_csv('genres.csv')
genre_df['id'] = range(1, len(genre_df) + 1)
genre_df.to_csv('genres.csv', index=False)
casts = df['Cast'].str.split(',').explode().tolist()

cast_df = pd.DataFrame({'Cast': list(set(casts))})

cast_df.to_csv('actors.csv', index=False)

cast_df = pd.read_csv('actors.csv')

cast_df['Cast'] = cast_df['Cast'].str.strip()

cast_df = cast_df.drop_duplicates()

cast_df.to_csv('actors.csv', index=False)
cast_df = pd.read_csv('actors.csv')
cast_df['id'] = range(1, len(cast_df) + 1)
cast_df.to_csv('actors.csv', index=False)