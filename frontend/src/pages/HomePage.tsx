import React from 'react';
import { 
  Box, 
  Typography, 
  Grid, 
  Card, 
  CardContent, 
  CardMedia, 
  Button, 
  Container,
  Paper
} from '@mui/material';
import { Link as RouterLink } from 'react-router-dom';
import SportsIcon from '@mui/icons-material/Sports';
import EmojiEventsIcon from '@mui/icons-material/EmojiEvents';
import GroupIcon from '@mui/icons-material/Group';

const HomePage: React.FC = () => {
  return (
    <Box>
      {/* Hero Section */}
      <Paper 
        sx={{
          position: 'relative',
          backgroundColor: 'grey.800',
          color: '#fff',
          mb: 4,
          backgroundSize: 'cover',
          backgroundRepeat: 'no-repeat',
          backgroundPosition: 'center',
          backgroundImage: `url(https://source.unsplash.com/random?football,stadium)`,
          borderRadius: 2,
          overflow: 'hidden'
        }}
      >
        <Box
          sx={{
            position: 'absolute',
            top: 0,
            bottom: 0,
            right: 0,
            left: 0,
            backgroundColor: 'rgba(0,0,0,.5)',
          }}
        />
        <Grid container>
          <Grid item md={6}>
            <Box
              sx={{
                position: 'relative',
                p: { xs: 3, md: 6 },
                pr: { md: 0 },
                minHeight: 300,
                display: 'flex',
                flexDirection: 'column',
                justifyContent: 'center'
              }}
            >
              <Typography component="h1" variant="h3" color="inherit" gutterBottom>
                Welcome to GoalTrack
              </Typography>
              <Typography variant="h5" color="inherit" paragraph>
                Your ultimate football tournament tracking application
              </Typography>
              <Button 
                variant="contained" 
                component={RouterLink} 
                to="/tournaments"
                size="large"
                sx={{ mt: 2, alignSelf: 'flex-start' }}
              >
                Explore Tournaments
              </Button>
            </Box>
          </Grid>
        </Grid>
      </Paper>

      {/* Features Section */}
      <Typography variant="h4" component="h2" gutterBottom align="center" sx={{ mb: 4 }}>
        Features
      </Typography>
      <Grid container spacing={4} sx={{ mb: 6 }}>
        <Grid item xs={12} md={4}>
          <Card sx={{ height: '100%', display: 'flex', flexDirection: 'column' }}>
            <Box sx={{ p: 2, display: 'flex', justifyContent: 'center' }}>
              <EmojiEventsIcon sx={{ fontSize: 60, color: 'primary.main' }} />
            </Box>
            <CardContent sx={{ flexGrow: 1 }}>
              <Typography gutterBottom variant="h5" component="h2" align="center">
                Track Tournaments
              </Typography>
              <Typography>
                Stay updated with all the latest football tournaments. View standings, schedules, and results in real-time.
              </Typography>
            </CardContent>
          </Card>
        </Grid>
        <Grid item xs={12} md={4}>
          <Card sx={{ height: '100%', display: 'flex', flexDirection: 'column' }}>
            <Box sx={{ p: 2, display: 'flex', justifyContent: 'center' }}>
              <SportsIcon sx={{ fontSize: 60, color: 'secondary.main' }} />
            </Box>
            <CardContent sx={{ flexGrow: 1 }}>
              <Typography gutterBottom variant="h5" component="h2" align="center">
                Follow Matches
              </Typography>
              <Typography>
                Get detailed information about matches including scores, venues, and attendance. Never miss an important game again.
              </Typography>
            </CardContent>
          </Card>
        </Grid>
        <Grid item xs={12} md={4}>
          <Card sx={{ height: '100%', display: 'flex', flexDirection: 'column' }}>
            <Box sx={{ p: 2, display: 'flex', justifyContent: 'center' }}>
              <GroupIcon sx={{ fontSize: 60, color: 'warning.main' }} />
            </Box>
            <CardContent sx={{ flexGrow: 1 }}>
              <Typography gutterBottom variant="h5" component="h2" align="center">
                Support Teams
              </Typography>
              <Typography>
                Follow your favorite teams across different tournaments and get notifications about their upcoming matches and results.
              </Typography>
            </CardContent>
          </Card>
        </Grid>
      </Grid>

      {/* Popular Tournaments Section */}
      <Typography variant="h4" component="h2" gutterBottom align="center" sx={{ mb: 4 }}>
        Popular Tournaments
      </Typography>
      <Grid container spacing={4} sx={{ mb: 6 }}>
        {['Premier League', 'La Liga', 'Bundesliga', 'Serie A'].map((tournament) => (
          <Grid item key={tournament} xs={12} sm={6} md={3}>
            <Card sx={{ height: '100%', display: 'flex', flexDirection: 'column' }}>
              <CardMedia
                component="img"
                height="140"
                image={`https://source.unsplash.com/random?football,${tournament.toLowerCase().replace(' ', '')}`}
                alt={tournament}
              />
              <CardContent sx={{ flexGrow: 1 }}>
                <Typography gutterBottom variant="h5" component="h2">
                  {tournament}
                </Typography>
                <Typography variant="body2" color="text.secondary">
                  View matches, standings, and team information for {tournament}.
                </Typography>
              </CardContent>
              <Box sx={{ p: 2 }}>
                <Button 
                  size="small" 
                  variant="outlined"
                  component={RouterLink}
                  to="/tournaments"
                >
                  View Details
                </Button>
              </Box>
            </Card>
          </Grid>
        ))}
      </Grid>

      {/* Call to Action */}
      <Paper sx={{ p: 4, bgcolor: 'primary.main', color: 'white', borderRadius: 2, mb: 4 }}>
        <Container maxWidth="md">
          <Typography variant="h5" align="center" gutterBottom>
            Ready to start tracking your favorite tournaments?
          </Typography>
          <Box sx={{ display: 'flex', justifyContent: 'center', mt: 2 }}>
            <Button 
              variant="contained" 
              color="secondary" 
              size="large"
              component={RouterLink}
              to="/register"
              sx={{ mx: 1 }}
            >
              Sign Up Now
            </Button>
            <Button 
              variant="outlined" 
              color="inherit" 
              size="large"
              component={RouterLink}
              to="/login"
              sx={{ mx: 1 }}
            >
              Login
            </Button>
          </Box>
        </Container>
      </Paper>
    </Box>
  );
};

export default HomePage; 