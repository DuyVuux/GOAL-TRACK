# GoalTrack Frontend

This is the frontend application for GoalTrack, a football tournament tracking system.

## Features

- User authentication (login, register)
- Tournament browsing and details
- Match tracking and results
- Team information
- User profile management

## Technology Stack

- React
- TypeScript
- Material UI
- React Router
- Axios

## Getting Started

### Prerequisites

- Node.js (v14 or later)
- npm or yarn

### Installation

1. Clone the repository
2. Navigate to the frontend directory:
   ```
   cd frontend
   ```
3. Install dependencies:
   ```
   npm install
   ```
   or
   ```
   yarn install
   ```

### Running the Application

To start the development server:

```
npm start
```

or

```
yarn start
```

The application will be available at http://localhost:3000

### Building for Production

```
npm run build
```

or

```
yarn build
```

## Project Structure

```
frontend/
├── public/                 # Static files
├── src/                    # Source files
│   ├── assets/             # Images, fonts, etc.
│   ├── components/         # Reusable components
│   │   └── layouts/        # Layout components
│   ├── pages/              # Page components
│   ├── services/           # API services
│   ├── types/              # TypeScript type definitions
│   ├── utils/              # Utility functions
│   ├── App.tsx             # Main App component
│   └── index.tsx           # Entry point
├── package.json            # Dependencies and scripts
└── tsconfig.json           # TypeScript configuration
```

## API Integration

The frontend connects to the GoalTrack backend API. API service functions are organized in the `src/services` directory.

## Authentication

The application uses JWT token-based authentication. The token is stored in localStorage and included in API requests via an Axios interceptor.

## Styling

Material UI is used for styling components with a customized theme defined in App.tsx.

## License

This project is licensed under the MIT License. 