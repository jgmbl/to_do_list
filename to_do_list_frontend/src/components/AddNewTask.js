import * as React from 'react';
import TextField from '@mui/material/TextField';
import NamesDropDownMenu from './NamesDropDownMenu';
import { Container, Paper, Stack } from '@mui/material';

export default function AddNewTask() {
  return (
    <Container
      component="form"
      sx={{
        display: 'flex',
        flexDirection: 'column',
        alignItems: 'center',
        '& > :not(style)': { m: 1, width: '25ch'},
      }}
      noValidate
      autoComplete="off"
    >
        <Paper elevation={3} sx={{ p: 3 }}>
            <Stack spacing={2}>
              <NamesDropDownMenu sx={{justifyContent: 'center'}}/>
            <TextField id="name" label="Name" variant="outlined" />
            <TextField id="task" label="Task" variant="outlined" />
            </Stack>
      </Paper>
    </Container>
  );
}
