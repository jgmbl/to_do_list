import * as React from 'react';
import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';
import NamesDropDownMenu from './NamesDropDownMenu';

export default function AddNewTask() {
  return (
    <Box
      component="form"
      sx={{
        display: 'flex',
        flexDirection: 'column',
        alignItems: 'center',
        '& > :not(style)': { m: 1, width: '25ch' },
      }}
      noValidate
      autoComplete="off"
    >
       <NamesDropDownMenu sx={{justifyContent: 'center', width: '100%'}}/>
      <TextField id="name" label="Name" variant="outlined" />
      <TextField id="task" label="Task" variant="outlined" />
    </Box>
  );
}
