import * as React from 'react';
import TextField from '@mui/material/TextField';
import NamesDropDownMenu from './NamesDropDownMenu';
import ButtonMainMenu from './ButtonMainMenu';
import { Container, Paper, Stack } from '@mui/material';

export default function AddNewTask() {
  return (
    <Container
      component="form"
      sx={{
        display: 'flex',
        flexDirection: 'column',
        alignItems: 'center',
        '& > :not(style)': { m: 1, width: '75ch'},
      }}
      noValidate
      autoComplete="off"
    >
        <Paper elevation={3} sx={{ p: 3 }}>
            <h1 style={{color: "#338BA8"}}>Welcome to to-do list app!</h1>
            <p style={{color: "#707070"}}>Create to-do lists and assign tasks to users.</p>
            <Stack spacing={2}>
            <b style={{color: "#707070"}}>Select a name from the list...</b>
              <NamesDropDownMenu sx={{justifyContent: 'center'}}/>
              <b style={{color: "#707070"}}>...or type your name:</b>
            <TextField id="name" label="Name" variant="outlined" />
            <b style={{color: "#707070"}}>Enter the task:</b>
            <TextField id="task" label="Task" variant="outlined" />
            <ButtonMainMenu/>
            </Stack>
      </Paper>
    </Container>
  );
}
