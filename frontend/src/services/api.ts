import axios, { AxiosRequestConfig } from 'axios';
import { 
  ApiResponse, 
  LoginRequest, 
  RegisterRequest, 
  User, 
  UserCreationRequest, 
  UserUpdateRequest, 
  UserPasswordRequest,
  Tournament,
  Team,
  Match,
  PaginatedResponse
} from '../types';

// Create axios instance with base configuration
const api = axios.create({
  baseURL: 'http://localhost:8080', // Adjust this to your backend URL
  headers: {
    'Content-Type': 'application/json',
  },
});

// Add authorization header to requests if token exists
api.interceptors.request.use((config) => {
  const token = localStorage.getItem('token');
  if (token) {
    config.headers.Authorization = `Bearer ${token}`;
  }
  return config;
});

// Authentication APIs
export const authAPI = {
  login: (data: LoginRequest) => 
    api.post<ApiResponse<{ token: string }>>('/auth/signin', data),
  
  register: (data: RegisterRequest) => 
    api.post<ApiResponse<User>>('/auth/signup', data),
  
  confirmEmail: (secretCode: string) => 
    api.get<void>(`/user/confirm-email?secretCode=${secretCode}`),
};

// User APIs
export const userAPI = {
  getUsers: (keyword?: string, sort?: string, page = 0, size = 20) => 
    api.get<ApiResponse<PaginatedResponse<User>>>('/user/list', { 
      params: { keyword, sort, page, size } 
    }),
  
  getUserById: (userId: number) => 
    api.get<ApiResponse<User>>(`/user/${userId}`),
  
  createUser: (user: UserCreationRequest) => 
    api.post<ApiResponse<User>>('/user/add', user),
  
  updateUser: (user: UserUpdateRequest) => 
    api.put<ApiResponse<void>>('/user/upd', user),
  
  changePassword: (data: UserPasswordRequest) => 
    api.patch<ApiResponse<void>>('/user/change-pwd', data),
  
  deleteUser: (userId: number) => 
    api.delete<ApiResponse<void>>(`/user/del/${userId}`),
};

// Tournament APIs
export const tournamentAPI = {
  getTournaments: (page = 0, size = 20) => 
    api.get<ApiResponse<PaginatedResponse<Tournament>>>('/tournaments', { 
      params: { page, size } 
    }),
  
  getTournamentById: (id: number) => 
    api.get<ApiResponse<Tournament>>(`/tournaments/${id}`),
  
  getTournamentTeams: (id: number) => 
    api.get<ApiResponse<Team[]>>(`/tournaments/${id}/teams`),
  
  getTournamentMatches: (id: number) => 
    api.get<ApiResponse<Match[]>>(`/tournaments/${id}/matches`),
};

// Match APIs
export const matchAPI = {
  getMatches: (page = 0, size = 20) => 
    api.get<ApiResponse<PaginatedResponse<Match>>>('/matches', { 
      params: { page, size } 
    }),
  
  getMatchById: (id: number) => 
    api.get<ApiResponse<Match>>(`/matches/${id}`),
};

// Team APIs
export const teamAPI = {
  getTeams: (page = 0, size = 20) => 
    api.get<ApiResponse<PaginatedResponse<Team>>>('/teams', { 
      params: { page, size } 
    }),
  
  getTeamById: (id: number) => 
    api.get<ApiResponse<Team>>(`/teams/${id}`),
  
  getTeamMatches: (id: number) => 
    api.get<ApiResponse<Match[]>>(`/teams/${id}/matches`),
};

export default api; 