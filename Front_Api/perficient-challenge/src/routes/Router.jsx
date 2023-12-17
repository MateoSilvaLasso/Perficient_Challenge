import React from 'react'
import {Route, Routes, BrowserRouter  } from  'react-router-dom';
import Tasks from '../pages/tasks';
import Login from '../pages/login';
import Register from '../pages/Register';

function Router() {
  return (
    <BrowserRouter>
        <Routes>
            <Route path="/" element = {<Login/>}/>
            <Route path="/app" element = {<Tasks/>}/>
            <Route path="/auth" element ={<Register/>}/>
        </Routes>
    </BrowserRouter>
  )
}

export default Router
