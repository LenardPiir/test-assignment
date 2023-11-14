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
import {Sector, SectorForm} from "../interface/SectorTypes";
import {
    getRegisteredCustomer,
    getSectors,
    saveForm,
    showToastMessage
} from "../service/SectorService";

export default function SectorComponent() {
    const [expanded, setExpanded] = useState<string[]>([]);
    const [selected, setSelected] = useState<string[]>([]);
    const [sectors, setSectors] = useState<Sector[]>([]);
    const [registeredCustomer, setRegisteredCustomer] = useState<SectorForm>();

    const [isNameEmpty, setIsNameEmpty] = useState<boolean>(false);
    const [isSectorsEmpty, setIsSectorsEmpty] = useState<boolean>(false);
    const [isCustomerAgreementEmpty, setIsCustomerAgreementEmpty] = useState<boolean>(false);

    useEffect(() => {
        getSectors().then((response) => {setSectors(response.data)})
        getRegisteredCustomer().then((response) => {setRegisteredCustomer(response.data)})
    }, []);

    const handleToggle = (event: SyntheticEvent, nodeIds: string[]) => {
        setExpanded(nodeIds);
    };

    const handleSelect = (event: SyntheticEvent, nodeIds: string[]) => {
        setSelected(nodeIds);
    };

    const validateForm = (data: FormData) => {
        setIsNameEmpty(false);
        setIsCustomerAgreementEmpty(false);
        setIsSectorsEmpty(false);

        if (!data.get('name')) {
            setIsNameEmpty(true);
        }
        if (!data.get('customerAgreement')) {
            setIsCustomerAgreementEmpty(true);
        }
        if (selected.length === 0) {
            setIsSectorsEmpty(true);
        }
        if (data.get('name') && data.get('customerAgreement') && selected.length > 0) {
            // In this case data cannot be null
            // @ts-ignore
            submitForm(data.get('name').toString(), selected);
        }
    }

    const submitForm = (name: string, selected: string[]) => {
        const formData: SectorForm = {
            name: name,
            sectors: selected,
            agreeToTerms: true
        }

        saveForm(formData).then((response) => {
            setRegisteredCustomer(response.data);
            showToastMessage();
        });
    }

    const handleSubmit = (event: FormEvent<HTMLFormElement>) => {
        event.preventDefault();
        const data = new FormData(event.currentTarget);

        validateForm(data);
    };

    function DisplayCustomerSectors() {
        return (
            <>{
                registeredCustomer?.sectors.map((sectorCode: string) =>
                    <Typography variant="caption" display="block" gutterBottom key={sectorCode}>
                        {sectorCode}
                    </Typography>
                )
            }</>
        )
    }

    function MapSectors({elements}: {elements: Sector[]}) {
        return (
            <>{
                elements?.map((element) =>
                        element.children.length > 0 ?
                        <TreeItem nodeId={element.code} label={element.name} key={element.code}>
                            {MapSectors({elements: element.children})}
                        </TreeItem>
                            :
                        <TreeItem nodeId={element.code} label={element.name} key={element.code}></TreeItem>
                )
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
                                required={true}
                                error={isNameEmpty}
                                helperText={isNameEmpty && "Name must not be empty."}
                                fullWidth
                                name="name"
                                label="Name"
                                type="name"
                                id="name"
                            />
                        </Grid>

                        <Grid item xs={12}>
                            <Typography component="h5" variant="h5">
                                Please select Sectors from below *:
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
                            <div>{
                                isSectorsEmpty ? (<Typography color={'#d32f2f'} >
                                    At least one sector must be selected.
                                </Typography>) : <div></div>
                            }</div>
                        </Grid>

                        <Grid item xs={12} mt={3}>
                            <FormControlLabel
                                control={
                                <Checkbox
                                    value="customerAgreement"
                                    color="primary"
                                    id="customerAgreement"
                                    name="customerAgreement"
                                />
                                }
                                required={true}
                                label="Agree to terms"
                            />
                            <div>{
                                isCustomerAgreementEmpty ? (
                                    <Typography color={'#d32f2f'} >
                                        You must agree to terms.
                                    </Typography>) : <div></div>
                            }</div>
                        </Grid>
                    </Grid>

                    <div> {
                        registeredCustomer ? (
                            <Grid item xs={12} mt={1}>
                                <Typography component="h6" variant="h6">
                                    Saved Sectors: <DisplayCustomerSectors></DisplayCustomerSectors>
                                </Typography>
                            </Grid>
                        ) : <div></div>
                    }</div>

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