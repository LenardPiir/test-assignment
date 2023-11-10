import {
    Box,
    Button,
    Checkbox,
    Container,
    CssBaseline,
    FormControlLabel,
    Grid,
    TextField,
    Typography
} from "@mui/material";
import {TreeView} from '@mui/x-tree-view/TreeView';
import {TreeItem} from '@mui/x-tree-view/TreeItem';
import {FormEvent, SyntheticEvent, useEffect, useState} from "react";
import ExpandMoreIcon from '@mui/icons-material/ExpandMore';
import ChevronRightIcon from '@mui/icons-material/ChevronRight';
import axios from "axios";

const baseURL = '/sectors'

interface Sector {
    code: string,
    name: string,
    children: Sector[]
}
export default function SectorComponent() {
    const [expanded, setExpanded] = useState<string[]>([]);
    const [selected, setSelected] = useState<string[]>([]);
    const [sectors, setSectors] = useState<Sector[]>([]);

    useEffect(() => {
        axios.get(baseURL).then((response) => {
            setSectors(response.data);
        });
    }, []);

    const handleToggle = (event: SyntheticEvent, nodeIds: string[]) => {
        setExpanded(nodeIds);
    };

    const handleSelect = (event: SyntheticEvent, nodeIds: string[]) => {
        setSelected(nodeIds);
    };

    const handleSubmit = (event: FormEvent<HTMLFormElement>) => {
        event.preventDefault();
        const data = new FormData(event.currentTarget);
    };

    function MapSectors({elements}: {elements: Sector[]}) {
        return (
            <>{
                elements?.map((element) =>
                <TreeItem nodeId={element.code} label={element.name} key={element.code}>
                    {MapSectors({elements: element.children})}
                </TreeItem>)
            }</>
        );
    }

    return (
        <Container component="main" maxWidth="xs">
            <CssBaseline />
            <Box
                sx={{
                    marginTop: 8,
                    display: 'flex',
                    flexDirection: 'column',
                    alignItems: 'center',
                }}
            >
                <Typography component="h1" variant="h5">
                    Please enter your name and pick the Sectors you are currently involved in.
                </Typography>

                <Box component="form" noValidate onSubmit={handleSubmit} sx={{ mt: 3 }}>
                    <Grid container spacing={2}>
                        <Grid item xs={12}>
                            <TextField
                                required
                                fullWidth
                                name="name"
                                label="Name"
                                type="name"
                                id="name"
                            />
                        </Grid>

                        <Grid item xs={12}>
                            <Typography component="h5" variant="h5">
                                Please select Sector from below *:
                            </Typography>
                        </Grid>

                        <Grid item xs={12}>

                            <TreeView
                                aria-label="controlled"
                                defaultCollapseIcon={<ExpandMoreIcon />}
                                defaultExpandIcon={<ChevronRightIcon />}
                                expanded={expanded}
                                selected={selected}
                                onNodeToggle={handleToggle}
                                onNodeSelect={handleSelect}
                                multiSelect
                            >
                                <MapSectors elements={sectors}></MapSectors>
                            </TreeView>
                        </Grid>

                        <Grid item xs={12} mt={1}>
                            <Typography component="h6" variant="h6">
                                Sector selected:
                            </Typography>
                        </Grid>

                        <Grid item xs={12} mt={3}>
                            <FormControlLabel
                                control={<Checkbox value="customerAgreement" color="primary" />}
                                label="Agree to terms *"
                            />
                        </Grid>
                    </Grid>
                    <Button
                        type="submit"
                        fullWidth
                        variant="contained"
                        sx={{ mt: 3, mb: 2 }}
                    >
                        Save
                    </Button>
                </Box>
            </Box>
        </Container>
    );
}