import React, { useState } from 'react';
import { Outlet } from 'react-router-dom';
import { 
  AppBar, 
  Box, 
  Toolbar, 
  IconButton, 
  Typography, 
  Menu, 
  Container, 
  Avatar, 
  Button, 
  Tooltip, 
  MenuItem,
  Drawer,
  List,
  ListItem,
  ListItemButton,
  ListItemIcon,
  ListItemText,
  Divider
} from '@mui/material';
import {
  Menu as MenuIcon,
  Sports as SportsIcon,
  EmojiEvents as TrophyIcon,
  Person as PersonIcon,
  Login as LoginIcon,
  Logout as LogoutIcon
} from '@mui/icons-material';
import { Link as RouterLink } from 'react-router-dom';

const pages = [
  { title: 'Tournaments', path: '/tournaments', icon: <TrophyIcon /> },
  { title: 'Matches', path: '/matches', icon: <SportsIcon /> }
];

const settings = [
  { title: 'Profile', path: '/profile', icon: <PersonIcon /> },
  { title: 'Logout', path: '/login', icon: <LogoutIcon /> }
];

const MainLayout: React.FC = () => {
  const [anchorElUser, setAnchorElUser] = useState<null | HTMLElement>(null);
  const [drawerOpen, setDrawerOpen] = useState(false);
  const isLoggedIn = false; // This would be determined by your auth state

  const handleOpenUserMenu = (event: React.MouseEvent<HTMLElement>) => {
    setAnchorElUser(event.currentTarget);
  };

  const handleCloseUserMenu = () => {
    setAnchorElUser(null);
  };

  const toggleDrawer = (open: boolean) => () => {
    setDrawerOpen(open);
  };

  return (
    <Box sx={{ display: 'flex', flexDirection: 'column', minHeight: '100vh' }}>
      <AppBar position="static">
        <Container maxWidth="xl">
          <Toolbar disableGutters>
            <SportsIcon sx={{ display: { xs: 'none', md: 'flex' }, mr: 1 }} />
            <Typography
              variant="h6"
              noWrap
              component={RouterLink}
              to="/"
              sx={{
                mr: 2,
                display: { xs: 'none', md: 'flex' },
                fontFamily: 'monospace',
                fontWeight: 700,
                letterSpacing: '.3rem',
                color: 'inherit',
                textDecoration: 'none',
              }}
            >
              GOALTRACK
            </Typography>

            <Box sx={{ flexGrow: 1, display: { xs: 'flex', md: 'none' } }}>
              <IconButton
                size="large"
                aria-label="menu"
                aria-controls="menu-appbar"
                aria-haspopup="true"
                onClick={toggleDrawer(true)}
                color="inherit"
              >
                <MenuIcon />
              </IconButton>
              <Drawer
                anchor="left"
                open={drawerOpen}
                onClose={toggleDrawer(false)}
              >
                <Box
                  sx={{ width: 250 }}
                  role="presentation"
                  onClick={toggleDrawer(false)}
                  onKeyDown={toggleDrawer(false)}
                >
                  <List>
                    {pages.map((page) => (
                      <ListItem key={page.title} disablePadding>
                        <ListItemButton component={RouterLink} to={page.path}>
                          <ListItemIcon>
                            {page.icon}
                          </ListItemIcon>
                          <ListItemText primary={page.title} />
                        </ListItemButton>
                      </ListItem>
                    ))}
                  </List>
                  <Divider />
                  {isLoggedIn ? (
                    <List>
                      {settings.map((setting) => (
                        <ListItem key={setting.title} disablePadding>
                          <ListItemButton component={RouterLink} to={setting.path}>
                            <ListItemIcon>
                              {setting.icon}
                            </ListItemIcon>
                            <ListItemText primary={setting.title} />
                          </ListItemButton>
                        </ListItem>
                      ))}
                    </List>
                  ) : (
                    <List>
                      <ListItem disablePadding>
                        <ListItemButton component={RouterLink} to="/login">
                          <ListItemIcon>
                            <LoginIcon />
                          </ListItemIcon>
                          <ListItemText primary="Login" />
                        </ListItemButton>
                      </ListItem>
                      <ListItem disablePadding>
                        <ListItemButton component={RouterLink} to="/register">
                          <ListItemIcon>
                            <PersonIcon />
                          </ListItemIcon>
                          <ListItemText primary="Register" />
                        </ListItemButton>
                      </ListItem>
                    </List>
                  )}
                </Box>
              </Drawer>
            </Box>

            <SportsIcon sx={{ display: { xs: 'flex', md: 'none' }, mr: 1 }} />
            <Typography
              variant="h5"
              noWrap
              component={RouterLink}
              to="/"
              sx={{
                mr: 2,
                display: { xs: 'flex', md: 'none' },
                flexGrow: 1,
                fontFamily: 'monospace',
                fontWeight: 700,
                letterSpacing: '.3rem',
                color: 'inherit',
                textDecoration: 'none',
              }}
            >
              GOALTRACK
            </Typography>
            <Box sx={{ flexGrow: 1, display: { xs: 'none', md: 'flex' } }}>
              {pages.map((page) => (
                <Button
                  key={page.title}
                  component={RouterLink}
                  to={page.path}
                  sx={{ my: 2, color: 'white', display: 'flex', alignItems: 'center', gap: 1 }}
                >
                  {page.icon}
                  {page.title}
                </Button>
              ))}
            </Box>

            {isLoggedIn ? (
              <Box sx={{ flexGrow: 0 }}>
                <Tooltip title="Open settings">
                  <IconButton onClick={handleOpenUserMenu} sx={{ p: 0 }}>
                    <Avatar alt="User" />
                  </IconButton>
                </Tooltip>
                <Menu
                  sx={{ mt: '45px' }}
                  id="menu-appbar"
                  anchorEl={anchorElUser}
                  anchorOrigin={{
                    vertical: 'top',
                    horizontal: 'right',
                  }}
                  keepMounted
                  transformOrigin={{
                    vertical: 'top',
                    horizontal: 'right',
                  }}
                  open={Boolean(anchorElUser)}
                  onClose={handleCloseUserMenu}
                >
                  {settings.map((setting) => (
                    <MenuItem 
                      key={setting.title} 
                      onClick={handleCloseUserMenu}
                      component={RouterLink}
                      to={setting.path}
                    >
                      <Box sx={{ display: 'flex', alignItems: 'center', gap: 1 }}>
                        {setting.icon}
                        <Typography textAlign="center">{setting.title}</Typography>
                      </Box>
                    </MenuItem>
                  ))}
                </Menu>
              </Box>
            ) : (
              <Box sx={{ display: 'flex', gap: 1 }}>
                <Button 
                  variant="outlined" 
                  color="inherit"
                  component={RouterLink}
                  to="/login"
                  startIcon={<LoginIcon />}
                >
                  Login
                </Button>
                <Button 
                  variant="contained" 
                  color="secondary"
                  component={RouterLink}
                  to="/register"
                  startIcon={<PersonIcon />}
                >
                  Register
                </Button>
              </Box>
            )}
          </Toolbar>
        </Container>
      </AppBar>
      
      <Box component="main" sx={{ flexGrow: 1, p: 3 }}>
        <Container maxWidth="lg">
          <Outlet />
        </Container>
      </Box>
      
      <Box
        component="footer"
        sx={{
          py: 3,
          px: 2,
          mt: 'auto',
          backgroundColor: (theme) =>
            theme.palette.mode === 'light'
              ? theme.palette.grey[200]
              : theme.palette.grey[800],
        }}
      >
        <Container maxWidth="sm">
          <Typography variant="body2" color="text.secondary" align="center">
            {'© '}
            {new Date().getFullYear()}
            {' GoalTrack - Football Tournament Tracking Application'}
          </Typography>
        </Container>
      </Box>
    </Box>
  );
};

export default MainLayout; 