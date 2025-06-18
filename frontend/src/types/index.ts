// User related interfaces
export interface User {
  id: number;
  firstName: string;
  lastName: string;
  gender?: 'MALE' | 'FEMALE' | 'OTHER';
  dateOfBirth?: string;
  phone?: string;
  email: string;
  username: string;
  type?: 'OWNER' | 'ADMIN' | 'USER';
  status?: 'NONE' | 'ACTIVE' | 'INACTIVE';
  createAt?: string;
  updateAt?: string;
  isActive?: boolean;
  lastLogin?: string;
  isEmailVerified?: boolean;
}

export interface UserCreationRequest {
  firstName: string;
  lastName: string;
  gender?: 'MALE' | 'FEMALE' | 'OTHER';
  dateOfBirth?: string;
  username: string;
  phone?: string;
  email: string;
  type?: 'OWNER' | 'ADMIN' | 'USER';
}

export interface UserUpdateRequest {
  id: number;
  firstName: string;
  lastName: string;
  gender?: 'MALE' | 'FEMALE' | 'OTHER';
  dateOfBirth?: string;
  phone?: string;
  email: string;
}

export interface UserPasswordRequest {
  id: number;
  oldPassword: string;
  newPassword: string;
  confirmPassword: string;
}

export interface LoginRequest {
  username: string;
  password: string;
}

export interface RegisterRequest {
  firstName: string;
  lastName: string;
  email: string;
  username: string;
  password: string;
  confirmPassword: string;
}

// Football related interfaces
export interface Tournament {
  id: number;
  name: string;
  country: string;
  season: string;
  logoUrl?: string;
  description?: string;
  startDate: string;
  endDate: string;
  apiSourceId?: string;
  createAt?: string;
  updateAt?: string;
}

export interface Team {
  id: number;
  name: string;
  country: string;
  logoUrl?: string;
  createAt?: string;
  updateAt?: string;
}

export interface Match {
  id: number;
  homeTeam: Team;
  awayTeam: Team;
  tournament: Tournament;
  matchDate: string;
  venue?: string;
  status: string;
  apiSourceId?: string;
  createAt?: string;
  updateAt?: string;
  attendance?: number;
}

export interface Standing {
  id: number;
  teamId: number;
  tournamentId: number;
  position: number;
  played: number;
  won: number;
  drawn: number;
  lost: number;
  goalsFor: number;
  goalsAgainst: number;
  points: number;
  team?: Team;
}

// API response interfaces
export interface ApiResponse<T> {
  status: number;
  message: string;
  data: T;
}

export interface PaginatedResponse<T> {
  content: T[];
  totalElements: number;
  totalPages: number;
  size: number;
  number: number;
  first: boolean;
  last: boolean;
  empty: boolean;
} 