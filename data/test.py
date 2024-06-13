import pandas as pd
import csv
import os
from train import train_model

# Load the data
df = pd.read_csv(r"C:\Users\admin\Desktop\DATN\data\imdb-movies-dataset.csv")

df['Title'] = df['Title'].str.lower()

# Train the model
cosine_sim, indices, movies = train_model(df)

indices = pd.Series(df.index, index=df['Title']).to_dict()

def get_recommendations(title, cosine_sim=cosine_sim):
    title = title.lower()
    idx = indices[title]
    sim_scores = list(enumerate(cosine_sim[idx]))
    sim_scores = sorted(sim_scores, key=lambda x: x[1], reverse=True)
    sim_scores = sim_scores[1:13]
    movie_indices = [i[0] for i in sim_scores]
    return movies['Title'].iloc[movie_indices]

with open('C:/Users/admin/Desktop/DATN/data/movie-name.txt', 'r') as file:
    movie_name = file.read().strip()

recommendations = get_recommendations(movie_name)

result = [(idx + 1, title) for idx, title in zip(recommendations.index, recommendations.values)]

if os.path.exists('C:/Users/admin/Desktop/DATN/data/result.csv'):
    os.remove('C:/Users/admin/Desktop/DATN/data/result.csv')

with open('C:/Users/admin/Desktop/DATN/data/result.csv', 'w', newline='') as file:
    writer = csv.writer(file)
    writer.writerow(['id', 'Title'])
    writer.writerows(result)
