import React, { createContext, useContext, useState } from 'react';
import Authentication from '../components/authentication'

const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
  const [name, setName] = useState('');

  return (
    <AuthContext.Provider value={{ name, setName }}>
      <Authentication/>
    </AuthContext.Provider>
  );
};

export const useAuth = () => {
  return useContext(AuthContext);
};