import React from 'react';
import { Box, Button, Container, Typography } from '@mui/material';
import { Link as RouterLink } from 'react-router-dom';
import SentimentVeryDissatisfiedIcon from '@mui/icons-material/SentimentVeryDissatisfied';

const NotFoundPage: React.FC = () => {
  return (
    <Container maxWidth="md">
      <Box
        sx={{
          display: 'flex',
          flexDirection: 'column',
          alignItems: 'center',
          justifyContent: 'center',
          minHeight: '60vh',
          textAlign: 'center',
          py: 5
        }}
      >
        <SentimentVeryDissatisfiedIcon sx={{ fontSize: 100, color: 'text.secondary', mb: 2 }} />
        
        <Typography variant="h1" component="h1" sx={{ mb: 2 }}>
          404
        </Typography>
        
        <Typography variant="h4" component="h2" sx={{ mb: 3 }}>
          Page Not Found
        </Typography>
        
        <Typography variant="body1" color="text.secondary" sx={{ mb: 4, maxWidth: 600 }}>
          The page you are looking for might have been removed, had its name changed,
          or is temporarily unavailable.
        </Typography>
        
        <Box sx={{ display: 'flex', gap: 2 }}>
          <Button
            variant="contained"
            component={RouterLink}
            to="/"
            size="large"
          >
            Go to Homepage
          </Button>
          
          <Button
            variant="outlined"
            onClick={() => window.history.back()}
            size="large"
          >
            Go Back
          </Button>
        </Box>
      </Box>
    </Container>
  );
};

export default NotFoundPage; 