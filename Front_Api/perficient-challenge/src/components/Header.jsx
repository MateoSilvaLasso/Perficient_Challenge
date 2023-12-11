import * as React from 'react';
import AppBar from '@mui/material/AppBar';
import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import Typography from '@mui/material/Typography';
import Button from '@mui/material/Button';
import IconButton from '@mui/material/IconButton';
import { MdOutlineTask } from "react-icons/md";


export default function ButtonAppBar() {
  return (
    <Box sx={{ flexGrow: 1}}>
      <AppBar position="static" sx={{backgroundColor: '#1F1F1F'}}>
        <Toolbar>   
        <MdOutlineTask size = "30px" style={{marginRight: "10px"}} />
          <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
            <b>TaskApp</b>
          </Typography>
        </Toolbar>
      </AppBar>
    </Box>
  );
}
