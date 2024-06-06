package com.csticorp.culqi.test.presentation.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.csticorp.culqi.test.R
import com.csticorp.culqi.test.data.response.DataUser
import com.csticorp.culqi.test.presentation.composables.DialogCallback
import com.csticorp.culqi.test.presentation.composables.DialogPrimary
import com.csticorp.culqi.test.presentation.composables.LoadImageWithURL
import com.csticorp.culqi.test.presentation.composables.LoadingSkeleton
import com.csticorp.culqi.test.presentation.composables.Spacer4
import com.csticorp.culqi.test.presentation.composables.Text16
import com.csticorp.culqi.test.presentation.composables.Text24
import com.csticorp.culqi.test.presentation.composables.animatedShimmer
import com.csticorp.culqi.test.presentation.ui.theme.BlueGray100
import com.csticorp.culqi.test.presentation.ui.theme.BlueGray300
import com.csticorp.culqi.test.presentation.ui.theme.ColorWhite
import com.csticorp.culqi.test.presentation.ui.theme.ContentInset
import com.csticorp.culqi.test.presentation.ui.theme.ContentInsetEight
import com.csticorp.culqi.test.presentation.ui.theme.ContentInsetFifty
import com.csticorp.culqi.test.presentation.ui.theme.ContentInsetForty
import com.csticorp.culqi.test.presentation.ui.theme.ContentInsetFour
import com.csticorp.culqi.test.presentation.ui.theme.ContentInsetOne
import com.csticorp.culqi.test.presentation.viewModels.NavigationViewModel
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navigationViewModel: NavigationViewModel, homeViewModel: HomeViewModels = hiltViewModel()
) {
    val getAllUsers by homeViewModel.getAllUsers.collectAsState()

    var dataList by remember { mutableStateOf(value = listOf<DataUser>()) }
    var messageError by remember { mutableStateOf(value = "") }
    var firstLoading by remember { mutableStateOf(value = false) }
    var moreLoading by remember { mutableStateOf(value = false) }

    LaunchedEffect(getAllUsers) {
        when (val state = getAllUsers) {
            HomeScreenState.First -> {
                firstLoading = false
                moreLoading = false
                messageError = ""
            }

            is HomeScreenState.FirstLoading -> {
                firstLoading = true
                moreLoading = false
                messageError = ""
            }

            HomeScreenState.MoreLoading -> {
                firstLoading = false
                moreLoading = true
                messageError = ""
            }

            is HomeScreenState.Error -> {
                firstLoading = false
                moreLoading = false
                messageError = state.error.message.orEmpty()
            }

            is HomeScreenState.Success -> {
                firstLoading = false
                moreLoading = false
                messageError = ""
                if (homeViewModel.page == 1) dataList = state.dataList
                else dataList += state.dataList
            }
        }
    }

    val listState = rememberLazyListState()
    val state = rememberPullToRefreshState()

    if (state.isRefreshing) {
        LaunchedEffect(Unit) {
            homeViewModel.isLastPage = false
            homeViewModel.page = 1
            homeViewModel.getAllUsers()
            state.endRefresh()
        }
    }

    LaunchedEffect(listState) {
        snapshotFlow {
            listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index
        }.collectLatest { index ->
            if (!moreLoading && index != null && index >= dataList.size - 1) {
                homeViewModel.page += 1
                homeViewModel.getAllUsers()
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ColorWhite)
    ) {
        if (firstLoading) {
            LoadingSkeleton()
        } else {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                LazyColumn(
                    state = listState,
                    modifier = Modifier
                        .nestedScroll(state.nestedScrollConnection)
                        .fillMaxSize()
                        .padding(
                            top = ContentInsetEight, bottom = ContentInsetEight
                        ),
                    verticalArrangement = Arrangement.spacedBy(ContentInsetFour)
                ) {
                    items(dataList) { item ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = ContentInset, end = ContentInset)
                                .border(
                                    width = ContentInsetOne,
                                    color = BlueGray300,
                                    shape = RoundedCornerShape(ContentInsetEight)
                                )
                                .background(
                                    color = BlueGray100,
                                    shape = RoundedCornerShape(ContentInsetEight)
                                ),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            LoadImageWithURL(
                                modifier = Modifier
                                    .padding(ContentInsetEight)
                                    .size(ContentInsetFifty)
                                    .clip(CircleShape),
                                photoUrl = item.avatar,
                                letter = item.letter
                            )
                            Column(
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(
                                        horizontal = ContentInsetEight,
                                        vertical = ContentInsetFour
                                    )
                            ) {
                                Text24(text = item.fullName)
                                Spacer4()
                                Text16(text = item.email)
                            }
                        }
                    }
                    if (moreLoading) {
                        item {
                            animatedShimmer()
                        }
                    }
                }
                PullToRefreshContainer(
                    state = state, modifier = Modifier.align(Alignment.TopCenter)
                )
            }
        }
    }

    if (messageError.isNotEmpty()) {
        DialogPrimary(
            setShowDialog = {
                messageError = ""
            },
            dialogCallback = DialogCallback({
                messageError = ""
            }, {}, {}, {
                messageError = ""
            }),
            primaryButtonText = stringResource(id = R.string.accept),
            primaryTitleText = stringResource(id = R.string.we_are_sorry),
            primaryBodyText = messageError
        )
    }
}