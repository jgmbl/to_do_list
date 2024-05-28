import * as React from 'react';
import { useTheme } from '@mui/material/styles';
import OutlinedInput from '@mui/material/OutlinedInput';
import InputLabel from '@mui/material/InputLabel';
import MenuItem from '@mui/material/MenuItem';
import FormControl from '@mui/material/FormControl';
import Select from '@mui/material/Select';
import { fetchNames } from './Names';

const ITEM_HEIGHT = 48;
const ITEM_PADDING_TOP = 8;
const MenuProps = {
  PaperProps: {
    style: {
      maxHeight: ITEM_HEIGHT * 4.5 + ITEM_PADDING_TOP,
      width: 250
    },
  },
};


function getStyles(name, personName, theme) {
  return {
    fontWeight:
      personName.indexOf(name) === -1
        ? theme.typography.fontWeightRegular
        : theme.typography.fontWeightMedium,
  };
}

export default function NamesDropDownMenu() {
  const theme = useTheme();
  const [personName, setPersonName] = React.useState([]);
  const [names, setNames] = React.useState([]);

  React.useEffect(() => {
    const fetchData = async () => {
        const fetchedNames = await fetchNames();
        setNames(fetchedNames);
    };
    fetchData();
  })

  const handleChange = (event) => {
    const {
      target: { value },
    } = event;
    setPersonName(
      // On autofill we get a stringified value.
      typeof value === 'string' ? value.split(',') : value,
    );
  };

  return (
    <FormControl sx={{ 
    display: 'flex',
    flexDirection: 'column',
    justifyContent: 'center',
    width: '75ch'
    }}>
    <InputLabel id="demo-multiple-name-label">Names</InputLabel>
    <Select
        labelId="demo-multiple-name-label"
        id="demo-multiple-name"
        value={personName}
        onChange={handleChange}
        input={<OutlinedInput label="Name" />}
        MenuProps={MenuProps}
    >
        {names.map((name) => (
        <MenuItem
            key={name.id}
            value={name.name}
            style={getStyles(name, personName, theme)}
        >
            {name.name}
        </MenuItem>
        ))}
    </Select>
    </FormControl>
  );
}