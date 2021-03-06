package org.zalando.stups.fullstop.hystrix;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.zalando.stups.clients.kio.*;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class HystrixKioOperationsTest {

    private static final String APP_ID = "foo";

    private static final Application APPLICATION = new Application();

    private static final List<ApplicationBase> APPS = newArrayList();

    private static final ArrayList<String> APPROVALS = newArrayList();

    private static final ArrayList<Approval> APPROVALS_BY_VERSION = newArrayList();

    private static final String APP_VERSION = "bar";

    private static final ArrayList<VersionBase> APP_VERSIONS = newArrayList();

    private static final Version VERSION = new Version();

    private static final CreateOrUpdateApplicationRequest CREATE_OR_UPDATE_APP = new CreateOrUpdateApplicationRequest();

    private static final CreateOrUpdateVersionRequest CREATE_OR_UPDATE_VERSION = new CreateOrUpdateVersionRequest();

    private static final ApprovalBase APPROVAL_BASE = new ApprovalBase();

    private HystrixKioOperations hystrixKioOperations;

    private KioOperations mockDelegate;

    @Before
    public void setUp() throws Exception {
        mockDelegate = mock(KioOperations.class);

        hystrixKioOperations = new HystrixKioOperations(mockDelegate);
    }

    @After
    public void tearDown() throws Exception {
        verifyNoMoreInteractions(mockDelegate);
    }

    @Test
    public void testListApplications() throws Exception {
        when(mockDelegate.listApplications()).thenReturn(APPS);
        assertThat(hystrixKioOperations.listApplications()).isSameAs(APPS);
        verify(mockDelegate).listApplications();
    }

    @Test
    public void testGetApplicationById() throws Exception {
        when(mockDelegate.getApplicationById(anyString())).thenReturn(APPLICATION);
        assertThat(hystrixKioOperations.getApplicationById(APP_ID)).isSameAs(APPLICATION);
        verify(mockDelegate).getApplicationById(same(APP_ID));
    }

    @Test
    public void testCreateOrUpdateApplication() throws Exception {
        hystrixKioOperations.createOrUpdateApplication(CREATE_OR_UPDATE_APP, APP_ID);
        verify(mockDelegate).createOrUpdateApplication(same(CREATE_OR_UPDATE_APP), same(APP_ID));
    }

    @Test
    public void testGetApplicationApprovalType() throws Exception {
        when(mockDelegate.getApplicationApprovalTypes(anyString())).thenReturn(APPROVALS);
        assertThat(hystrixKioOperations.getApplicationApprovalTypes(APP_ID)).isSameAs(APPROVALS);
        verify(mockDelegate).getApplicationApprovalTypes(same(APP_ID));
    }

    @Test
    public void testGetApplicationVersionApprovals() throws Exception {
        when(mockDelegate.getApplicationVersionApprovals(anyString(), anyString())).thenReturn(APPROVALS_BY_VERSION);
        assertThat(hystrixKioOperations.getApplicationVersionApprovals(APP_ID, APP_VERSION)).isSameAs(APPROVALS_BY_VERSION);
        verify(mockDelegate).getApplicationVersionApprovals(same(APP_ID), same(APP_VERSION));
    }

    @Test
    public void testGetApplicationVersions() throws Exception {
        when(mockDelegate.getApplicationVersions(anyString())).thenReturn(APP_VERSIONS);
        assertThat(hystrixKioOperations.getApplicationVersions(APP_ID)).isSameAs(APP_VERSIONS);
        verify(mockDelegate).getApplicationVersions(same(APP_ID));
    }

    @Test
    public void testGetApplicationVersion() throws Exception {
        when(mockDelegate.getApplicationVersion(anyString(), anyString())).thenReturn(VERSION);
        assertThat(hystrixKioOperations.getApplicationVersion(APP_ID, APP_VERSION)).isSameAs(VERSION);
        verify(mockDelegate).getApplicationVersion(same(APP_ID), same(APP_VERSION));
    }

    @Test
    public void testCreateOrUpdateVersion() throws Exception {
        hystrixKioOperations.createOrUpdateVersion(CREATE_OR_UPDATE_VERSION, APP_ID, APP_VERSION);
        verify(mockDelegate).createOrUpdateVersion(same(CREATE_OR_UPDATE_VERSION), same(APP_ID), same(APP_VERSION));
    }

    @Test
    public void testApproveApplicationVersion() throws Exception {
        hystrixKioOperations.approveApplicationVersion(APPROVAL_BASE, APP_ID, APP_VERSION);
        verify(mockDelegate).approveApplicationVersion(same(APPROVAL_BASE), same(APP_ID), same(APP_VERSION));
    }
}