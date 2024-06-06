import * as React from 'react';
import TextField from '@mui/material/TextField';
import NamesDropDownMenu from './NamesDropDownMenu';
import ButtonMainMenu from './ButtonMainMenu';
import { Container, Paper, Stack } from '@mui/material';

export default function AddNewTask() {
    const [task, setTask] = React.useState('');
    const [name, setName] = React.useState('');
    const [namesMenu, setNamesMenu] = React.useState('');
    const [finalNameValue, setFinalNameValue] = React.useState('');
    const [emptyTextField, setEmptyTextField] = React.useState(false);

    function handleTaskFieldChange(event) {
        setTask(event.target.value);
        setEmptyTextField(false);
    }

    function handleNameFieldChange(event) {
        setName(event.target.value);
        setEmptyTextField(false);
    }

    const handleNamesMenuChange = (value) => {
        setNamesMenu(value);
    }

    function handleSubmit(event) {
        event.preventDefault();

        if (task.trim() === '') {
            setEmptyTextField(true);
        }

        let valueName;
        if (name.trim() === '') {
            valueName = namesMenu;
        } else if (namesMenu === '') {
            valueName = name;
        }

        setFinalNameValue(valueName);
    }
    
  return (
    <Container
      component="form"
      onSubmit={handleSubmit}
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
            <p style={{color: "#707070"}}>Check the tasks in the menu in the top left corner.</p>
            <Stack spacing={2}>
            <b style={{color: "#707070"}}>Select name from the list...</b>
              <NamesDropDownMenu onNameChange={handleNamesMenuChange} sx={{justifyContent: 'center'}}/>
              <b style={{color: "#707070"}}>...or type your name:</b>
            <TextField
            error={emptyTextField}
            helperText={emptyTextField ? 'Name is required' : ''}
            value={name}
            onChange={handleNameFieldChange}
            id="name"
            label="Name"
            variant="outlined"
            />
            <b style={{color: "#707070"}}>Enter the task:</b>
            <TextField 
            error={emptyTextField}
            helperText={emptyTextField ? 'Task is required' : ''}
            id="task"
            label="Task"
            variant="outlined"
            value={task}
            onChange={handleTaskFieldChange}
            />
            <ButtonMainMenu onClick={handleSubmit}/>
            <p>Final name value: {finalNameValue}</p>
            </Stack>
      </Paper>
    </Container>
  );
}