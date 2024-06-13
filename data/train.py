import pandas as pd
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.metrics.pairwise import linear_kernel

def train_model(df):
    # Subset the dataframe
    movies = df[[#'Poster', 
            'Title', 'Year', 'Certificate', 'Genre',
            'Rating', 'Metascore', 
            'Director', 'Cast','Votes', 
            'Description'
            ]].copy()

    # Handle missing values
    movies['Cast'] = movies['Cast'].fillna(movies['Cast'].mode()[0])
    movies['Year'] = movies['Year'].astype(int)
    movies['Description'] = movies['Description'].fillna('')

    # Create TF-IDF matrix
    tfidf = TfidfVectorizer(stop_words='english')
    tfiff_matrix = tfidf.fit_transform(movies['Description'])

    # Calculate cosine similarity
    cosine_sim = linear_kernel(tfiff_matrix, tfiff_matrix)

    # Create indices
    indices = pd.Series(movies.index, index=movies['Title']).drop_duplicates()

    return cosine_sim, indices, movies