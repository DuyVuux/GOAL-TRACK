import React, { useState, useEffect } from 'react';
import { 
  Container, 
  Typography, 
  Grid, 
  Card, 
  CardContent, 
  CardMedia, 
  Button,
  Box,
  CircularProgress,
  Pagination,
  TextField,
  InputAdornment,
  Alert
} from '@mui/material';
import { Link as RouterLink } from 'react-router-dom';
import SearchIcon from '@mui/icons-material/Search';
import { tournamentAPI } from '../services/api';
import { Tournament } from '../types';

const TournamentsPage: React.FC = () => {
  const [tournaments, setTournaments] = useState<Tournament[]>([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);
  const [page, setPage] = useState(0);
  const [totalPages, setTotalPages] = useState(0);
  const [searchTerm, setSearchTerm] = useState('');
  const pageSize = 12;

  useEffect(() => {
    fetchTournaments();
  }, [page]);

  const fetchTournaments = async () => {
    setLoading(true);
    setError(null);
    try {
      const response = await tournamentAPI.getTournaments(page, pageSize);
      if (response.data.status === 200) {
        setTournaments(response.data.data.content);
        setTotalPages(response.data.data.totalPages);
      } else {
        setError('Failed to fetch tournaments');
      }
    } catch (err) {
      setError('An error occurred while fetching tournaments');
      console.error('Error fetching tournaments:', err);
    } finally {
      setLoading(false);
    }
  };

  const handlePageChange = (event: React.ChangeEvent<unknown>, value: number) => {
    setPage(value - 1);
  };

  const handleSearchChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setSearchTerm(event.target.value);
  };

  // Mock data for display since we don't have actual API implementation yet
  const mockTournaments: Tournament[] = [
    {
      id: 1,
      name: 'Premier League',
      country: 'England',
      season: '2023/2024',
      logoUrl: 'https://source.unsplash.com/random?football,premierleague',
      description: 'The Premier League, often referred to as the English Premier League or the EPL, is the top level of the English football league system.',
      startDate: '2023-08-11',
      endDate: '2024-05-19'
    },
    {
      id: 2,
      name: 'La Liga',
      country: 'Spain',
      season: '2023/2024',
      logoUrl: 'https://source.unsplash.com/random?football,laliga',
      description: 'The Campeonato Nacional de Liga de Primera División, commonly known as La Liga, is the men\'s top professional football division of the Spanish football league system.',
      startDate: '2023-08-13',
      endDate: '2024-05-26'
    },
    {
      id: 3,
      name: 'Bundesliga',
      country: 'Germany',
      season: '2023/2024',
      logoUrl: 'https://source.unsplash.com/random?football,bundesliga',
      description: 'The Bundesliga is a professional association football league in Germany. At the top of the German football league system, it is the country\'s primary football competition.',
      startDate: '2023-08-18',
      endDate: '2024-05-18'
    },
    {
      id: 4,
      name: 'Serie A',
      country: 'Italy',
      season: '2023/2024',
      logoUrl: 'https://source.unsplash.com/random?football,seriea',
      description: 'Serie A, also called Serie A TIM for sponsorship reasons, is a professional league competition for football clubs located at the top of the Italian football league system.',
      startDate: '2023-08-19',
      endDate: '2024-05-26'
    },
    {
      id: 5,
      name: 'Ligue 1',
      country: 'France',
      season: '2023/2024',
      logoUrl: 'https://source.unsplash.com/random?football,ligue1',
      description: 'Ligue 1, officially known as Ligue 1 Uber Eats for sponsorship reasons, is the French professional league for men\'s association football clubs.',
      startDate: '2023-08-12',
      endDate: '2024-05-19'
    },
    {
      id: 6,
      name: 'UEFA Champions League',
      country: 'Europe',
      season: '2023/2024',
      logoUrl: 'https://source.unsplash.com/random?football,championsleague',
      description: 'The UEFA Champions League is an annual club football competition organised by the Union of European Football Associations and contested by top-division European clubs.',
      startDate: '2023-09-19',
      endDate: '2024-06-01'
    }
  ];

  // Filter tournaments based on search term
  const filteredTournaments = searchTerm
    ? mockTournaments.filter(tournament => 
        tournament.name.toLowerCase().includes(searchTerm.toLowerCase()) ||
        tournament.country.toLowerCase().includes(searchTerm.toLowerCase())
      )
    : mockTournaments;

  return (
    <Container maxWidth="lg" sx={{ mt: 4, mb: 4 }}>
      <Typography variant="h4" component="h1" gutterBottom>
        Football Tournaments
      </Typography>
      
      <Box sx={{ mb: 4 }}>
        <TextField
          fullWidth
          variant="outlined"
          placeholder="Search tournaments by name or country"
          value={searchTerm}
          onChange={handleSearchChange}
          InputProps={{
            startAdornment: (
              <InputAdornment position="start">
                <SearchIcon />
              </InputAdornment>
            ),
          }}
        />
      </Box>

      {error && (
        <Alert severity="error" sx={{ mb: 2 }}>
          {error}
        </Alert>
      )}

      {loading ? (
        <Box sx={{ display: 'flex', justifyContent: 'center', my: 4 }}>
          <CircularProgress />
        </Box>
      ) : (
        <>
          <Grid container spacing={4}>
            {filteredTournaments.map((tournament) => (
              <Grid item key={tournament.id} xs={12} sm={6} md={4}>
                <Card sx={{ height: '100%', display: 'flex', flexDirection: 'column' }}>
                  <CardMedia
                    component="img"
                    height="200"
                    image={tournament.logoUrl}
                    alt={tournament.name}
                  />
                  <CardContent sx={{ flexGrow: 1 }}>
                    <Typography gutterBottom variant="h5" component="h2">
                      {tournament.name}
                    </Typography>
                    <Typography variant="body2" color="text.secondary" gutterBottom>
                      {tournament.country} • {tournament.season}
                    </Typography>
                    <Typography variant="body2" paragraph>
                      {tournament.description && tournament.description.length > 100 
                        ? `${tournament.description.substring(0, 100)}...` 
                        : tournament.description}
                    </Typography>
                    <Typography variant="body2" color="text.secondary">
                      {new Date(tournament.startDate).toLocaleDateString()} - {new Date(tournament.endDate).toLocaleDateString()}
                    </Typography>
                  </CardContent>
                  <Box sx={{ p: 2 }}>
                    <Button 
                      variant="contained" 
                      fullWidth
                      component={RouterLink}
                      to={`/tournaments/${tournament.id}`}
                    >
                      View Details
                    </Button>
                  </Box>
                </Card>
              </Grid>
            ))}
          </Grid>
          
          {totalPages > 1 && (
            <Box sx={{ display: 'flex', justifyContent: 'center', mt: 4 }}>
              <Pagination 
                count={totalPages} 
                page={page + 1} 
                onChange={handlePageChange} 
                color="primary" 
              />
            </Box>
          )}
        </>
      )}
    </Container>
  );
};

export default TournamentsPage; 